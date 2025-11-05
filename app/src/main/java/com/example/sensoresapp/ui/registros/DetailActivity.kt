package com.example.sensoresapp.ui.registros

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sensoresapp.MiApp
import com.example.sensoresapp.R
import com.example.sensoresapp.data.model.RegistroMedico
import com.example.sensoresapp.viewmodel.RegistroViewModel
import com.example.sensoresapp.viewmodel.RegistroViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var registroViewModel: RegistroViewModel
    private var registroId: Long = 0
    private var currentRegistro: RegistroMedico? = null // Almacena el objeto si estamos actualizando

    // Vistas
    private lateinit var nombreInput: EditText
    private lateinit var diagnosticoInput: EditText
    private lateinit var fechaTextView: TextView
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail) // Asegúrate de crear este layout

        // 1. INYECCIÓN DE DEPENDENCIAS
        val app = application as MiApp
        val factory = RegistroViewModelFactory(app.registroRepository)
        registroViewModel = ViewModelProvider(this, factory)[RegistroViewModel::class.java]

        // 2. INICIALIZACIÓN DE VISTAS
        nombreInput = findViewById(R.id.edit_text_nombre)
        diagnosticoInput = findViewById(R.id.edit_text_diagnostico)
        fechaTextView = findViewById(R.id.text_view_fecha_registro)
        saveButton = findViewById(R.id.button_save)

        // 3. RECUPERAR ID (Determina si es CREAR o ACTUALIZAR)
        registroId = intent.getLongExtra("REGISTRO_ID", 0)

        if (registroId != 0L) {
            // Modo ACTUALIZAR (CRUD: Actualizar)
            title = "Editar Registro Médico"

            // Cargar datos del registro y rellenar campos
            registroViewModel.getRegistroById(registroId).observe(this, Observer { registro ->
                registro?.let {
                    currentRegistro = it
                    nombreInput.setText(it.nombre)
                    diagnosticoInput.setText(it.diagnostico)
                    fechaTextView.text = "Fecha de registro: ${it.fecha}"
                }
            })
        } else {
            // Modo CREAR (CRUD: Crear)
            title = "Nuevo Registro Médico"
            fechaTextView.text = "Fecha de registro: ${getCurrentDate()}"
        }

        // 4. MANEJAR GUARDADO (Crear o Actualizar)
        saveButton.setOnClickListener {
            saveRegistro()
        }
    }

    private fun saveRegistro() {
        val nombre = nombreInput.text.toString().trim()
        val diagnostico = diagnosticoInput.text.toString().trim()

        if (nombre.isBlank() || diagnostico.isBlank()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (registroId != 0L && currentRegistro != null) {
            // ACTUALIZAR (Usamos el ID y la fecha original, pero nuevos nombre/diagnóstico)
            val updatedRegistro = currentRegistro!!.copy(
                nombre = nombre,
                diagnostico = diagnostico
            )
            registroViewModel.update(updatedRegistro)
            Toast.makeText(this, "Registro actualizado correctamente.", Toast.LENGTH_SHORT).show()
        } else {
            // CREAR (Nuevo registro)
            val newRegistro = RegistroMedico(
                nombre = nombre,
                diagnostico = diagnostico,
                fecha = getCurrentDate()
            )
            registroViewModel.insert(newRegistro)
            Toast.makeText(this, "Registro creado correctamente.", Toast.LENGTH_SHORT).show()
        }
        finish() // Volver a MainActivity
    }

    // Función de utilidad para obtener la fecha y hora actual
    private fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return formatter.format(Date())
    }
}