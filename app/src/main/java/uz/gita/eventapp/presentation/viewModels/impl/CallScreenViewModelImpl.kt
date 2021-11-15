package uz.gita.eventapp.presentation.viewModels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.eventapp.presentation.viewModels.CallScreenViewModel
import javax.inject.Inject

@HiltViewModel
class CallScreenViewModelImpl @Inject constructor():CallScreenViewModel,ViewModel() {
    override val initValues = MutableLiveData<Unit>()
    override val backButton = MutableLiveData<Unit>()

    init {
        setInitValues()
    }

    override fun setInitValues() {
        initValues.value = Unit
    }

    override fun openBackButton() {
        backButton.value = Unit
    }

}