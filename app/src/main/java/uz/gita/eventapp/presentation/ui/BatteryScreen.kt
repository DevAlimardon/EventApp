package uz.gita.eventapp.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
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
import uz.gita.eventapp.data.prefs.MyPrefs
import uz.gita.eventapp.databinding.ScreenBatteryBinding
import uz.gita.eventapp.presentation.viewModels.BatterScreenViewModel
import uz.gita.eventapp.presentation.viewModels.impl.BatteryScreenViewModelImpl
import uz.gita.eventapp.service.MyService
import uz.gita.eventapp.utils.gone
import uz.gita.eventapp.utils.scope
import uz.gita.eventapp.utils.visible

@AndroidEntryPoint
class BatteryScreen : Fragment(R.layout.screen_battery) {

    private val binding by viewBinding(ScreenBatteryBinding::bind)
    private val viewModel: BatterScreenViewModel by viewModels<BatteryScreenViewModelImpl>()
    private val navController by lazy { findNavController() }
    private val prefs = MyPrefs.getPrefs()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {


        backBtn.setOnClickListener {
            viewModel.clickBackBtn()
        }
        viewModel.initValues.observe(viewLifecycleOwner, observeInitValues)
        viewModel.backBtn.observe(this@BatteryScreen, observeBackBtn)


        viewBatteryMain.setOnClickListener {
            prefs.batterStatus = !prefs.batterStatus
            if(!prefs.allAnnouncements && prefs.batterStatus){
                prefs.allAnnouncements = prefs.batterStatus
            }
            batteryMainSwitch.isChecked = prefs.batterStatus
            viewModel.setInitValues()
            startService()
        }
        viewConnect.setOnClickListener {
            prefs.powerConnect = !prefs.powerConnect
            chargerConnectSwitch.isChecked = prefs.powerConnect

            viewModel.setInitValues()
            startService()

        }
        viewDisconnect.setOnClickListener {
            prefs.powerDisConnect = !prefs.powerDisConnect
            chargerDisConnectSwitch.isChecked = prefs.powerDisConnect
            viewModel.setInitValues()
            startService()
        }
        viewLowBattery.setOnClickListener {
            prefs.batteryLow = !prefs.batteryLow
            lowBatterySwitch.isChecked = prefs.batteryLow
            viewModel.setInitValues()
            startService()
        }


    }

    private val observeInitValues = Observer<Unit> {
        if (!prefs.batterStatus || !prefs.allAnnouncements ) {


            binding.line.gone()
            binding.chargerConnectAnim.gone()
            binding.chargerConnectTxt.gone()
            binding.chargerConnectSwitch.gone()

            binding.chargerDisConnectAnim.gone()
            binding.chargerDisConnectTxt.gone()
            binding.chargerDisConnectSwitch.gone()

            binding.lowBatteryAnim.gone()
            binding.lowBatteryTxt.gone()
            binding.lowBatterySwitch.gone()
            binding.viewConnect.gone()
            binding.viewDisconnect.gone()
            binding.viewLowBattery.gone()

            binding.batteryMainSwitch.isChecked = false
        } else {
            binding.line.visible()
            binding.chargerConnectAnim.visible()
            binding.chargerConnectTxt.visible()
            binding.chargerConnectSwitch.visible()

            binding.chargerDisConnectAnim.visible()
            binding.chargerDisConnectTxt.visible()
            binding.chargerDisConnectSwitch.visible()

            binding.lowBatteryAnim.visible()
            binding.lowBatteryTxt.visible()
            binding.lowBatterySwitch.visible()

            binding.viewConnect.visible()
            binding.viewDisconnect.visible()
            binding.viewLowBattery.visible()


            binding.chargerDisConnectSwitch.isChecked = prefs.powerDisConnect
            binding.chargerConnectSwitch.isChecked = prefs.powerConnect
            binding.lowBatterySwitch.isChecked = prefs.batteryLow
            binding.batteryMainSwitch.isChecked = prefs.batterStatus

        }


    }
    private val observeBackBtn = Observer<Unit> {
        navController.popBackStack()
    }
    private fun startService() {
        val intent = Intent(requireActivity(), MyService::class.java)
        intent.putExtra("data","data")
        if (Build.VERSION.SDK_INT >= 26) {
            requireActivity().startForegroundService(intent)
        } else requireActivity().startService(intent)
    }
}