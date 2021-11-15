package uz.gita.eventapp.presentation.viewModels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.eventapp.data.prefs.MyPrefs
import uz.gita.eventapp.presentation.viewModels.MainScreenViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModelImpl @Inject constructor() : MainScreenViewModel, ViewModel() {
    override val batterScreen = MutableLiveData<Unit>()
    override val registerValues = MutableLiveData<Unit>()
    override val screenScreen = MutableLiveData<Unit>()
    override val airScreen = MutableLiveData<Unit>()
    override val settingsScreen = MutableLiveData<Unit>()
    override val callScreen = MutableLiveData<Unit>()
    override val smsScreen = MutableLiveData<Unit>()
    private val prefs:MyPrefs = MyPrefs.getPrefs()

    init {
        prefs.isFirst = false
        registerValues.value = Unit
    }

    override fun openBatteryScreen() {
        batterScreen.value = Unit
    }

    override fun openScreenScreen() {
        screenScreen.value = Unit
    }

    override fun openAirScreen() {
        airScreen.value = Unit
    }

    override fun openSettingsScreen() {
        settingsScreen.value = Unit
    }

    override fun openCallScreen() {
        callScreen.value = Unit
    }

    override fun openSmSScreen() {
        smsScreen.value = Unit
    }
}