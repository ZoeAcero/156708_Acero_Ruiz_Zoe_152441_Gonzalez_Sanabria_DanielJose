package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registros_medicos")
data class RegistroMedico(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "paciente_nombre")
    val nombre: String,

    @ColumnInfo(name = "diagnostico")
    val diagnostico: String,

    @ColumnInfo(name = "fecha_registro")
    val fecha: String
)