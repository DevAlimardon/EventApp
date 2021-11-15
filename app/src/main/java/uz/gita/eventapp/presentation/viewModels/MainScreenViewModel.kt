package uz.gita.eventapp.presentation.viewModels

import androidx.lifecycle.LiveData

interface MainScreenViewModel {
    val batterScreen:LiveData<Unit>
    val registerValues:LiveData<Unit>
    val screenScreen:LiveData<Unit>
    val airScreen:LiveData<Unit>
    val settingsScreen:LiveData<Unit>
    val callScreen:LiveData<Unit>
    val smsScreen:LiveData<Unit>

    fun openBatteryScreen()
    fun openScreenScreen()
    fun openAirScreen()
    fun openSettingsScreen()
    fun openCallScreen()
    fun openSmSScreen()
}