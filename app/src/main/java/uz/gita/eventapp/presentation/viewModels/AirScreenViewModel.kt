package uz.gita.eventapp.presentation.viewModels

import androidx.lifecycle.LiveData

interface AirScreenViewModel {
    val initValue:LiveData<Unit>
    val backBtn:LiveData<Unit>

    fun setInitValues()
    fun openBackButton()
}