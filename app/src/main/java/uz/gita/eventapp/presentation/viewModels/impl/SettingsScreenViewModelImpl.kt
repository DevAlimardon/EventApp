package uz.gita.eventapp.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.eventapp.presentation.viewModels.SettingsScreenViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModelImpl @Inject constructor():SettingsScreenViewModel,ViewModel() {
    override val backBtn = MutableLiveData<Unit>()
    override val initValues = MutableLiveData<Unit>()

    init {
        setInitValues()
    }

    override fun openBackBtn() {
        backBtn.value = Unit
    }

    override fun setInitValues() {
        initValues.value = Unit
    }

}