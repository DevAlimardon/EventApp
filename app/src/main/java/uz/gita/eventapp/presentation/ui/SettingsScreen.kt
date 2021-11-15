package uz.gita.eventapp.presentation.ui

import android.annotation.SuppressLint
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
import uz.gita.eventapp.databinding.ScreenSettingsBinding
import uz.gita.eventapp.presentation.viewModels.SettingsScreenViewModel
import uz.gita.eventapp.presentation.viewModels.impl.SettingsScreenViewModelImpl
import uz.gita.eventapp.utils.scope
import uz.gita.eventapp.utils.toast

@AndroidEntryPoint
class SettingsScreen:Fragment(R.layout.screen_settings) {

    private val binding by viewBinding(ScreenSettingsBinding::bind)
    private val viewModel:SettingsScreenViewModel by viewModels<SettingsScreenViewModelImpl>()
    private val navController by lazy { findNavController() }
    private val prefs = MyPrefs.getPrefs()
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        backBtn.setOnClickListener {
            viewModel.openBackBtn()
        }

        viewModel.backBtn.observe(this@SettingsScreen,observeBackBtn)
        viewModel.initValues.observe(viewLifecycleOwner,observeInitValues)

        viewSettings.setOnClickListener {
            prefs.allAnnouncements = !prefs.allAnnouncements
            if(prefs.allAnnouncements){
                toast("Turn on all announcements")
            }else{
                toast("Turn off all announcements")
            }
            viewModel.setInitValues()
        }

    }
    private val observeInitValues = Observer<Unit>{
        binding.screenSettingsSwitch.isChecked = prefs.allAnnouncements
    }
    private val observeBackBtn = Observer<Unit>{
        navController.popBackStack()
    }
}