package uz.gita.eventapp.presentation.viewModels

import androidx.lifecycle.LiveData

interface ScreenScreenViewModel {
    val backButton:LiveData<Unit>
    val initValues:LiveData<Unit>

    fun setBackButton()
    fun setInitValues()
}