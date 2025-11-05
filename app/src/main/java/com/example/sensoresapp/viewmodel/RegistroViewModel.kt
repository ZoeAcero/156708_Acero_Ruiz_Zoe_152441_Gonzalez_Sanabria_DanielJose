package com.example.sensoresapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sensoresapp.data.model.RegistroMedico
import com.example.sensoresapp.data.repository.RegistroRepository
import kotlinx.coroutines.launch

class RegistroViewModel(private val repository: RegistroRepository) : ViewModel() {

    val allRegistros = repository.allRegistros.asLiveData()

    fun insert(registro: RegistroMedico) = viewModelScope.launch {
        repository.insert(registro)
    }

    fun update(registro: RegistroMedico) = viewModelScope.launch {
        repository.update(registro)
    }

    fun delete(registro: RegistroMedico) = viewModelScope.launch {
        repository.delete(registro)
    }

    fun getRegistroById(id: Long) = repository.getRegistroById(id).asLiveData()
}