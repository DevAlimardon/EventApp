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
import uz.gita.eventapp.databinding.ScreenScreenBinding
import uz.gita.eventapp.presentation.viewModels.ScreenScreenViewModel
import uz.gita.eventapp.presentation.viewModels.impl.ScreenScreenViewModelImpl
import uz.gita.eventapp.utils.gone
import uz.gita.eventapp.utils.scope
import uz.gita.eventapp.utils.visible

@AndroidEntryPoint
class ScreenScreen : Fragment(R.layout.screen_screen) {

    private val binding by viewBinding(ScreenScreenBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel: ScreenScreenViewModel by viewModels<ScreenScreenViewModelImpl>()
    private val prefs = MyPrefs.getPrefs()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {


        backBtn.setOnClickListener { viewModel.setBackButton() }

        viewModel.backButton.observe(this@ScreenScreen, observeBackButton)
        viewModel.initValues.observe(viewLifecycleOwner, observeInitValues)


        viewScreen.setOnClickListener {
            prefs.screenStatus = !prefs.screenStatus
            if(!prefs.allAnnouncements && prefs.screenStatus){
                prefs.allAnnouncements = prefs.screenStatus
            }
            viewModel.setInitValues()
        }
        viewOff.setOnClickListener {
            prefs.screenOff = !prefs.screenOff
            viewModel.setInitValues()
        }
        viewOn.setOnClickListener{
            prefs.screenOn = !prefs.screenOn
            viewModel.setInitValues()
        }
    }

    private val observeInitValues = Observer<Unit> {
        if (!prefs.screenStatus || !prefs.allAnnouncements) {
            binding.apply {
                screenMainSwitch.isChecked = false
                line.gone()
                screenOffAnim.gone()
                screenOffTxt.gone()
                screenOffSwitch.gone()
                screenOnAnim.gone()
                screemOnTxt.gone()
                screenOnSwitch.gone()
            }
        } else {
            binding.apply {
                screenMainSwitch.isChecked = prefs.screenStatus
                line.visible()
                screenOffAnim.visible()
                screenOffTxt.visible()
                screenOffSwitch.visible()
                screenOnAnim.visible()
                screemOnTxt.visible()
                screenOnSwitch.visible()
                screenOnSwitch.isChecked = prefs.screenOn
                screenOffSwitch.isChecked = prefs.screenOff
            }
        }
    }


    private val observeBackButton = Observer<Unit> {
        navController.popBackStack()
    }

}