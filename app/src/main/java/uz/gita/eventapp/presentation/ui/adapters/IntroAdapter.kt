package uz.gita.eventapp.presentation.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.eventapp.presentation.ui.pages.FirstPage
import uz.gita.eventapp.presentation.ui.pages.SecondPage

class IntroAdapter(fm:FragmentActivity):FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FirstPage()
            else -> SecondPage()
        }
    }
}