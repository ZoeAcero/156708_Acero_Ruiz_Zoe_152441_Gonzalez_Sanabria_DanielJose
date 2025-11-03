package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.repository

import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.db.RegistroDao
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.model.RegistroMedico
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