package uz.gita.eventapp.presentation.viewModels

import androidx.lifecycle.LiveData

interface BatterScreenViewModel {
    val backBtn:LiveData<Unit>
    val initValues:LiveData<Unit>

    fun clickBackBtn()
    fun setInitValues()

}