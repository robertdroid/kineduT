package net.devrob.kinedut.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
}