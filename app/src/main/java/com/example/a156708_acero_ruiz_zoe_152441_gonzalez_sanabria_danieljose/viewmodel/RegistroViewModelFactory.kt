package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.repository.RegistroRepository

/**
 * Factory para crear una instancia de RegistroViewModel con el RegistroRepository inyectado.
 */
class RegistroViewModelFactory(private val repository: RegistroRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Asegura que la clase solicitada sea RegistroViewModel
        if (modelClass.isAssignableFrom(RegistroViewModel::class.java)) {
            // Devuelve una nueva instancia de RegistroViewModel pasando el repositorio
            return RegistroViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}