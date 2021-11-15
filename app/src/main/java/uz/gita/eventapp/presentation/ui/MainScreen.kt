package uz.gita.eventapp.presentation.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.eventapp.R
import uz.gita.eventapp.app.App
import uz.gita.eventapp.databinding.ScreenMainBinding
import uz.gita.eventapp.presentation.viewModels.MainScreenViewModel
import uz.gita.eventapp.presentation.viewModels.impl.MainScreenViewModelImpl
import uz.gita.eventapp.service.MyService
import uz.gita.eventapp.utils.checkPermissions
import uz.gita.eventapp.utils.scope

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val viewModel: MainScreenViewModel by viewModels<MainScreenViewModelImpl>()
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val navController by lazy { findNavController() }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        startService()
        requireActivity().checkPermissions(arrayOf(Manifest.permission.READ_PHONE_STATE,Manifest.permission.RECEIVE_SMS)) {

        }
        viewModel.batterScreen.observe(this@MainScreen, observeBatteryScreen)
        viewModel.registerValues.observe(viewLifecycleOwner, observeRegisterValues)
        viewModel.screenScreen.observe(this@MainScreen,observeScreenScreen)
        viewModel.airScreen.observe(this@MainScreen,observeAirScreen)
        viewModel.settingsScreen.observe(this@MainScreen,observeSettingsScreen)
        viewModel.callScreen.observe(this@MainScreen,observeCallScreen)
        viewModel.smsScreen.observe(this@MainScreen,observeSmsScreen)
        batteryCard.setOnClickListener { viewModel.openBatteryScreen() }
        screenCard.setOnClickListener { viewModel.openScreenScreen() }
        planeCard.setOnClickListener { viewModel.openAirScreen() }
        settingsCard.setOnClickListener { viewModel.openSettingsScreen() }
        callCard.setOnClickListener { viewModel.openCallScreen() }
        smsCard.setOnClickListener { viewModel.openSmSScreen() }

    }
    private val observeSmsScreen = Observer<Unit>{
        navController.navigate(MainScreenDirections.actionMainScreenToSMSScreen())
    }
    private val observeCallScreen = Observer<Unit>{
        navController.navigate(MainScreenDirections.actionMainScreenToCallScreen())
    }
    private val observeSettingsScreen = Observer<Unit>{
        navController.navigate(MainScreenDirections.actionMainScreenToSettingsScreen())
    }
    private val observeAirScreen = Observer<Unit>{
        navController.navigate(MainScreenDirections.actionMainScreenToAirScreen())
    }
    private val observeScreenScreen = Observer<Unit>{
        navController.navigate(MainScreenDirections.actionMainScreenToScreenScreen())
    }

    private val observeBatteryScreen = Observer<Unit> {
        navController.navigate(MainScreenDirections.actionMainScreenToBatteryScreen())
    }
    private val observeRegisterValues = Observer<Unit> {

    }

    private fun startService() {
        val intent = Intent(requireActivity(), MyService::class.java)
        intent.putExtra("data","data")
        if (Build.VERSION.SDK_INT >= 26) {
            requireActivity().startForegroundService(intent)
        } else requireActivity().startService(intent)
    }
}