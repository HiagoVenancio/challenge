package com.example.projetoportugal

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

open class BaseViewModel : ViewModel() {

    private val _errorMessage = MutableStateFlow(String())
    val errorMessage = _errorMessage

    fun setErrorMessage(message: String?) {
        _errorMessage.update {
            message.toString()
        }
    }
}