package uz.gita.eventapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.eventapp.R
import uz.gita.eventapp.databinding.ScreenIntroBinding
import uz.gita.eventapp.presentation.ui.adapters.IntroAdapter
import uz.gita.eventapp.utils.gone
import uz.gita.eventapp.utils.scope
import uz.gita.eventapp.utils.visible

class IntroScreen:Fragment(R.layout.screen_intro) {
    private val binding by viewBinding(ScreenIntroBinding::bind)
    private val navController by lazy { findNavController() }
    private val introAdapter by lazy { IntroAdapter(requireActivity()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        pager.adapter = introAdapter
        pager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> nextScreen.gone()
                    1 -> nextScreen.visible()
                }
            }
        })
        nextScreen.setOnClickListener {
          navController.navigate(IntroScreenDirections.actionIntroScreenToMainScreen())
        }
    }
}