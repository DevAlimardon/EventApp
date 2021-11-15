package uz.gita.eventapp.presentation.viewModels

import androidx.lifecycle.LiveData

interface CallScreenViewModel {
    val initValues:LiveData<Unit>
    val backButton:LiveData<Unit>

    fun setInitValues()
    fun openBackButton()
}