package com.example.sensoresapp.data.repository

import com.example.sensoresapp.data.db.RegistroDao
import com.example.sensoresapp.data.model.RegistroMedico
import kotlinx.coroutines.flow.Flow

class RegistroRepository(private val registroDao: RegistroDao) {

    val allRegistros: Flow<List<RegistroMedico>> = registroDao.getAllRegistros()

    suspend fun insert(registro: RegistroMedico) {
        registroDao.insert(registro)
    }

    suspend fun update(registro: RegistroMedico) {
        registroDao.update(registro)
    }

    suspend fun delete(registro: RegistroMedico) {
        registroDao.delete(registro)
    }

    fun getRegistroById(id: Long): Flow<RegistroMedico> {
        return registroDao.getRegistroById(id)
    }
}