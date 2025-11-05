package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.MiApp
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.R
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.ui.registros.MainActivity
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.viewmodel.AuthViewModel
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.viewmodel.AuthViewModelFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    // Suponiendo que estos IDs existen en tu layout activity_login.xml
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var goToSignUpButton: Button // Botón para ir a la pantalla de registro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Asegúrate de crear este layout

        // 1. INYECCIÓN MANUAL DE DEPENDENCIAS (usando el Factory)
        val app = application as MiApp

        // --- CORRECCIÓN AQUÍ: Usar AuthViewModelFactory ---
        val factory = AuthViewModelFactory(app.authRepository)
        viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]


        // 2. CHEQUEO INICIAL (Protección)
        // Si el usuario ya está logeado, saltamos la pantalla de login.
        if (viewModel.isLoggedIn()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return // Salir del onCreate
        }

        // Inicialización de vistas (Reemplaza con tus IDs reales)
        emailInput = findViewById(R.id.edit_text_email_login)
        passwordInput = findViewById(R.id.edit_text_password_login)
        loginButton = findViewById(R.id.button_login)
        goToSignUpButton = findViewById(R.id.button_go_to_signup)


        // 3. LÓGICA DE INICIO DE SESIÓN
        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.signIn(email, password)
            } else {
                Toast.makeText(this, "Rellena todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. OBSERVACIÓN del resultado del login (añadido para completar el flujo)
        viewModel.authState.observe(this) { result ->
            if (result.isSuccess) {
                Toast.makeText(this, "Sesión iniciada correctamente.", Toast.LENGTH_SHORT).show()
                // Redirigir a la Activity principal
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Error de login: ${result.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
            }
        }

        // 5. Navegación a Registro (añadido para completar el flujo)
        goToSignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}