package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _authState = MutableLiveData<Result<Boolean>>()
    val authState: LiveData<Result<Boolean>> = _authState

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.signUp(email, password)
            if (result.isSuccess) {
                _authState.value = Result.success(true)
            } else {
                _authState.value = Result.failure(result.exceptionOrNull() ?: Exception("Error desconocido"))
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.signIn(email, password)
            if (result.isSuccess) {
                _authState.value = Result.success(true)
            } else {
                _authState.value = Result.failure(result.exceptionOrNull() ?: Exception("Error desconocido"))
            }
        }
    }

    fun signOut() {
        repository.signOut()
    }

    fun isLoggedIn(): Boolean = repository.isUserLoggedIn()
}