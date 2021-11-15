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
import uz.gita.eventapp.databinding.ScreenCallBinding
import uz.gita.eventapp.presentation.viewModels.CallScreenViewModel
import uz.gita.eventapp.presentation.viewModels.impl.CallScreenViewModelImpl
import uz.gita.eventapp.utils.gone
import uz.gita.eventapp.utils.scope
import uz.gita.eventapp.utils.visible

@AndroidEntryPoint
class CallScreen : Fragment(R.layout.screen_call) {
    private val binding by viewBinding(ScreenCallBinding::bind)
    private val viewModel:CallScreenViewModel by viewModels<CallScreenViewModelImpl>()
    private val navController by lazy { findNavController() }
    private val prefs = MyPrefs.getPrefs()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        viewModel.initValues.observe(viewLifecycleOwner,observeInitValues)

        viewModel.backButton.observe(this@CallScreen,observeCallScreen)

        backBtn.setOnClickListener { viewModel.openBackButton() }

        viewCallMain.setOnClickListener {
            prefs.callStatus = !prefs.callStatus
            if(!prefs.allAnnouncements && prefs.callStatus){
                prefs.allAnnouncements = prefs.callStatus
            }
            viewModel.setInitValues()
        }
        viewIncoming.setOnClickListener {
            prefs.incomingCall = !prefs.incomingCall
            viewModel.setInitValues()
        }
        viewOutgoing.setOnClickListener {
            prefs.outgoingCall = !prefs.outgoingCall
            viewModel.setInitValues()
        }

    }
    private val observeCallScreen = Observer<Unit>{
        navController.popBackStack()
    }
    private val observeInitValues = Observer<Unit>{
       if(!prefs.allAnnouncements || !prefs.callStatus){
           binding.apply {
               callMainSwitch.isChecked = false
               line.gone()
               incomingCallAnim.gone()
               incomingCallTxt.gone()
               incomingCallSwitch.gone()
               outgoingCallAnim.gone()
               outgoingCallTxt.gone()
               outgoingCallSwitch.gone()
               viewOutgoing.gone()
               viewIncoming.gone()
           }
       }else{
           binding.apply {
               callMainSwitch.isChecked = prefs.callStatus
               line.visible()
               incomingCallAnim.visible()
               incomingCallTxt.visible()
               incomingCallSwitch.visible()
               outgoingCallAnim.visible()
               outgoingCallTxt.visible()
               outgoingCallSwitch.visible()
               viewOutgoing.visible()
               viewIncoming.visible()

               incomingCallSwitch.isChecked = prefs.incomingCall
               outgoingCallSwitch.isChecked = prefs.outgoingCall

           }
       }
    }
}