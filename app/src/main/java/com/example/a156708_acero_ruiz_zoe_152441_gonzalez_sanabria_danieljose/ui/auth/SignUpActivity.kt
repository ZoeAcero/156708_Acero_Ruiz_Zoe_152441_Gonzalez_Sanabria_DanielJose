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
class SignUpActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel

    // Suponiendo que estos IDs existen en tu layout activity_sign_up.xml
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up) // Asegúrate de crear este layout

        // 1. INYECCIÓN MANUAL DE DEPENDENCIAS (usando el Factory)
        val app = application as MiApp
        val factory = AuthViewModelFactory(app.authRepository)
        viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]

        // Inicialización de vistas (Reemplaza con tus IDs reales)
        emailInput = findViewById(R.id.edit_text_email_signup)
        passwordInput = findViewById(R.id.edit_text_password_signup)
        signUpButton = findViewById(R.id.button_signup)

        // 2. LÓGICA DE REGISTRO
        signUpButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isNotEmpty() && password.length >= 6) {
                viewModel.signUp(email, password)
            } else {
                Toast.makeText(this, "Introduce un email y una contraseña de al menos 6 caracteres.", Toast.LENGTH_LONG).show()
            }
        }

        // 3. OBSERVACIÓN del resultado del registro
        viewModel.authState.observe(this) { result ->
            if (result.isSuccess) {
                Toast.makeText(this, "Registro exitoso. Bienvenido.", Toast.LENGTH_SHORT).show()
                // Redirigir a la Activity principal después del registro
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Error de registro: ${result.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}