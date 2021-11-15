package uz.gita.eventapp.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.eventapp.presentation.viewModels.AirScreenViewModel
import javax.inject.Inject

@HiltViewModel
class AirScreenViewModelImpl @Inject constructor():AirScreenViewModel,ViewModel() {
    override val initValue = MutableLiveData<Unit>()
    override val backBtn = MutableLiveData<Unit>()

    init {
        setInitValues()
    }

    override fun setInitValues() {
        initValue.value = Unit
    }

    override fun openBackButton() {
        backBtn.value = Unit
    }

}