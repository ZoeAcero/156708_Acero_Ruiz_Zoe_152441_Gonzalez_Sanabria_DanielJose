package com.example.sensoresapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
// CORRECCIÓN 1: Importar el Repositorio de Registros
import com.example.sensoresapp.data.repository.RegistroRepository
// IMPORTACIÓN ELIMINADA: import com.example.a156708...repository.AuthRepository

/**
 * Factory para crear una instancia de RegistroViewModel con el RegistroRepository inyectado.
 */
// CORRECCIÓN 2: El constructor debe recibir RegistroRepository
class RegistroViewModelFactory(private val repository: RegistroRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Asegura que la clase solicitada sea RegistroViewModel
        if (modelClass.isAssignableFrom(RegistroViewModel::class.java)) {
            // Devuelve una nueva instancia de RegistroViewModel pasando el repositorio
            // NOTA: RegistroViewModel debe ser capaz de aceptar un RegistroRepository
            return RegistroViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}