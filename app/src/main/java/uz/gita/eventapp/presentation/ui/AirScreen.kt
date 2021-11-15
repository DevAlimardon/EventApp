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
import uz.gita.eventapp.databinding.ScreenAirBinding
import uz.gita.eventapp.presentation.viewModels.AirScreenViewModel
import uz.gita.eventapp.presentation.viewModels.impl.AirScreenViewModelImpl
import uz.gita.eventapp.utils.scope

@AndroidEntryPoint
class AirScreen :Fragment(R.layout.screen_air){

    private val binding:ScreenAirBinding by viewBinding(ScreenAirBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel:AirScreenViewModel by viewModels<AirScreenViewModelImpl>()
    private val prefs = MyPrefs.getPrefs()

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {

        viewModel.initValue.observe(viewLifecycleOwner,observeInitValues)
        viewModel.backBtn.observe(this@AirScreen,observeBackBtn)

        viewAir.setOnClickListener {
            prefs.airMode = !prefs.airMode
            if(!prefs.allAnnouncements && prefs.airMode){
                prefs.allAnnouncements = prefs.airMode
            }
            viewModel.setInitValues()
        }
        backBtn.setOnClickListener { viewModel.openBackButton() }
    }
    private val observeBackBtn = Observer<Unit>{
        navController.popBackStack()
    }
    private val observeInitValues = Observer<Unit>{
        binding.screenAirSwitch.isChecked = !(!prefs.airMode || !prefs.allAnnouncements)
    }
}