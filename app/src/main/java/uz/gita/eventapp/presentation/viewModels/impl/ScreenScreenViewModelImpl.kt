package uz.gita.eventapp.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.eventapp.presentation.viewModels.ScreenScreenViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenScreenViewModelImpl @Inject constructor():ScreenScreenViewModel,ViewModel() {
    override val backButton = MutableLiveData<Unit>()
    override val initValues = MutableLiveData<Unit>()

    init {
        setInitValues()
    }

    override fun setBackButton() {
        backButton.value = Unit
    }

    override fun setInitValues() {
        initValues.value = Unit
    }

}