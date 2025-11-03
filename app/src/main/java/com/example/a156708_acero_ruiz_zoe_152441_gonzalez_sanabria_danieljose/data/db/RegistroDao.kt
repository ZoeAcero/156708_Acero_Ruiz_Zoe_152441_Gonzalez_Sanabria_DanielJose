package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.model.RegistroMedico
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistroDao {

    @Insert
    suspend fun insert(registro: RegistroMedico)

    @Query("SELECT * FROM registros_medicos ORDER BY id DESC")
    fun getAllRegistros(): Flow<List<RegistroMedico>>

    @Query("SELECT * FROM registros_medicos WHERE id = :registroId")
    fun getRegistroById(registroId: Long): Flow<RegistroMedico>

    @Update
    suspend fun update(registro: RegistroMedico)

    @Delete
    suspend fun delete(registro: RegistroMedico)
}