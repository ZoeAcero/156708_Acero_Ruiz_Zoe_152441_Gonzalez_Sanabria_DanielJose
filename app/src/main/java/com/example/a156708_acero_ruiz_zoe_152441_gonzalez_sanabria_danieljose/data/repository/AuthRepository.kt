package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.repository

import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.source.FirebaseDataSource
import com.google.firebase.auth.AuthResult

class AuthRepository(private val firebaseDataSource: FirebaseDataSource) {

    suspend fun signUp(email: String, password: String): Result<AuthResult> = try {
        val result = firebaseDataSource.signUp(email, password)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun signIn(email: String, password: String): Result<AuthResult> = try {
        val result = firebaseDataSource.signIn(email, password)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    fun isUserLoggedIn(): Boolean {
        return firebaseDataSource.getCurrentUser() != null
    }

    fun signOut() {
        firebaseDataSource.signOut()
    }
}