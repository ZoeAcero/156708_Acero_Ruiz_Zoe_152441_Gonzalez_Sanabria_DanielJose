package com.example.sensoresapp

import android.app.Application
import com.example.sensoresapp.data.db.AppDatabase
import com.example.sensoresapp.data.repository.AuthRepository
import com.example.sensoresapp.data.repository.RegistroRepository
import com.example.sensoresapp.data.source.FirebaseDataSource

class MiApp : Application() {
    // Inicialización perezosa (lazy) de la BD para que solo se cree al usarse por primera vez
    private val database by lazy { AppDatabase.getDatabase(this) }

    // Repositorio de Registros (usa el DAO de la BD)
    val registroRepository by lazy { RegistroRepository(database.registroDao()) }

    // Repositorio de Autenticación (usa la Fuente de Datos de Firebase)
    private val firebaseDataSource by lazy { FirebaseDataSource() }
    val authRepository by lazy { AuthRepository(firebaseDataSource) }
}