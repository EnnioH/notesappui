package com.example.notesappui.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _currentFragment = MutableLiveData<String>()
    val currentFragment: LiveData<String> = _currentFragment

    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> = _counter

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        _counter.value = 0
        _currentFragment.value = "home"
        _isLoading.value = false
    }

    fun incrementCounter() {
        _counter.value = (_counter.value ?: 0) + 1
    }

    fun resetCounter() {
        _counter.value = 0
    }

    fun setCurrentFragment(fragmentName: String) {
        _currentFragment.value = fragmentName
    }

    fun simulateDataLoading() {
        _isLoading.value = true
        viewModelScope.launch {
            delay(2000)
            _isLoading.value = false
        }
    }

    fun setError(message: String) {
        _errorMessage.value = message
    }

    fun clearError() {
        _errorMessage.value = ""
    }

    override fun onCleared() {
        super.onCleared()
    }
}