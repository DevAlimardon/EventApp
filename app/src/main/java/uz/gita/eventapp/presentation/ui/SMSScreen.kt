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
import uz.gita.eventapp.databinding.ScreenSmsBinding
import uz.gita.eventapp.presentation.viewModels.CallScreenViewModel
import uz.gita.eventapp.presentation.viewModels.SMSScreenViewModel
import uz.gita.eventapp.presentation.viewModels.impl.CallScreenViewModelImpl
import uz.gita.eventapp.presentation.viewModels.impl.SMSScreenViewModelImpl
import uz.gita.eventapp.utils.gone
import uz.gita.eventapp.utils.scope
import uz.gita.eventapp.utils.visible

@AndroidEntryPoint
class SMSScreen:Fragment(R.layout.screen_sms) {

    private val binding by viewBinding(ScreenSmsBinding::bind)
    private val viewModel: SMSScreenViewModel by viewModels<SMSScreenViewModelImpl>()
    private val navController by lazy { findNavController() }
    private val prefs = MyPrefs.getPrefs()
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        viewModel.backButton.observe(this@SMSScreen,observeCallScreen)
        viewModel.initValues.observe(viewLifecycleOwner,observeInitValues)

        viewSmsMain.setOnClickListener {
            prefs.smsStatus = !prefs.smsStatus
            if(!prefs.allAnnouncements && prefs.smsStatus){
                prefs.allAnnouncements = prefs.smsStatus
            }
            viewModel.setInitValues()
        }
//        viewIncoming.setOnClickListener {
//            prefs.incomingSMS = !prefs.incomingSMS
//            viewModel.setInitValues()
//        }
//        viewOutgoing.setOnClickListener {
//            prefs.outgoingSMS = !prefs.outgoingSMS
//            viewModel.setInitValues()
//        }


        backBtn.setOnClickListener { viewModel.openBackButton() }
    }
    private val observeCallScreen = Observer<Unit>{
        navController.popBackStack()
    }

    private val observeInitValues = Observer<Unit>{
        if(!prefs.allAnnouncements || !prefs.smsStatus){
            binding.apply {
                smsMainSwitch.isChecked = false
//                line.gone()
//                incomingSmsAnim.gone()
//                incomingSmsSwitch.gone()
//                incomingSmsTxt.gone()
//                outgoingSmsAnim.gone()
//                outgoingSmsSwitch.gone()
//                outgoingSmsTxt.gone()
//                viewIncoming.gone()
//                viewOutgoing.gone()

            }
        }else{
            binding.apply {
                smsMainSwitch.isChecked = prefs.smsStatus
//                line.visible()
//                incomingSmsAnim.visible()
//                incomingSmsSwitch.visible()
//                incomingSmsTxt.visible()
//                outgoingSmsAnim.visible()
//                outgoingSmsSwitch.visible()
//                outgoingSmsTxt.visible()
//                viewIncoming.visible()
//                viewOutgoing.visible()
//
//                incomingSmsSwitch.isChecked = prefs.incomingSMS
//                outgoingSmsSwitch.isChecked = prefs.outgoingSMS

            }
        }
    }
}