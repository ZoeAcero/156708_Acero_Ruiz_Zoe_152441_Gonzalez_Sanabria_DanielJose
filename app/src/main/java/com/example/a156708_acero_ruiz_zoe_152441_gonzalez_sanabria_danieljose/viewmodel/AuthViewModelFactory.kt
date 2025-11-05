package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.repository.AuthRepository

/**
 * Factory para crear AuthViewModel con su AuthRepository.
 */
class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
