package uz.gita.eventapp.presentation.viewModels

import androidx.lifecycle.LiveData

interface SettingsScreenViewModel {
    val backBtn:LiveData<Unit>
    val initValues:LiveData<Unit>

    fun openBackBtn()
    fun setInitValues()
}