package uz.gita.eventapp.presentation.viewModels

import androidx.lifecycle.LiveData

interface SMSScreenViewModel {
    val initValues: LiveData<Unit>
    val backButton: LiveData<Unit>

    fun setInitValues()
    fun openBackButton()
}