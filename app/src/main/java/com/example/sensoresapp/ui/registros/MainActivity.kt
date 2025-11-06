package com.example.sensoresapp.ui.registros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sensoresapp.MiApp
import com.example.sensoresapp.R
import com.example.sensoresapp.ui.auth.LoginActivity
import com.example.sensoresapp.viewmodel.AuthViewModel
import com.example.sensoresapp.viewmodel.AuthViewModelFactory
import com.example.sensoresapp.viewmodel.RegistroViewModel
import com.example.sensoresapp.viewmodel.RegistroViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var registroViewModel: RegistroViewModel
    private lateinit var registroAdapter: RegistroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1️⃣ INYECCIÓN DE DEPENDENCIAS (usando MiApp y Factory)
        val app = application as MiApp
        val registroFactory = RegistroViewModelFactory(app.registroRepository)
        registroViewModel = ViewModelProvider(this, registroFactory)[RegistroViewModel::class.java]

        // 2️⃣ CONFIGURACIÓN DEL RECYCLERVIEW
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_registros)
        registroAdapter = RegistroAdapter { registro ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("REGISTRO_ID", registro.id)
            startActivity(intent)
        }
        recyclerView.adapter = registroAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3️⃣ OBSERVACIÓN DE DATOS (CRUD: Leer)
        registroViewModel.allRegistros.observe(this, Observer { registros ->
            registros?.let { registroAdapter.submitList(it) }
        })

        // 4️⃣ SWIPE PARA BORRAR (CRUD: Borrar)
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val registroToDelete = registroAdapter.getRegistroAt(viewHolder.adapterPosition)
                registroViewModel.delete(registroToDelete)
                Toast.makeText(this@MainActivity, "Registro de ${registroToDelete.nombre} eliminado", Toast.LENGTH_SHORT).show()
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)

        // 5️⃣ FAB PARA CREAR NUEVO REGISTRO (CRUD: Crear)
        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        // 6️⃣ BOTÓN DE CERRAR SESIÓN (Firebase Auth)
        val logoutButton = findViewById<Button>(R.id.button_sign_out)
        logoutButton.setOnClickListener {
            val authFactory = AuthViewModelFactory(app.authRepository)
            val authViewModel = ViewModelProvider(this, authFactory)[AuthViewModel::class.java]

            authViewModel.signOut() // Cierra sesión en Firebase
            Toast.makeText(this, "Sesión cerrada correctamente", Toast.LENGTH_SHORT).show()

            // Redirige al Login y limpia el back stack
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
