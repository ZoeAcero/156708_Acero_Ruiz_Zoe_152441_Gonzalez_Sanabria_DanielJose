package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.ui.registros

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.MiApp
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.R
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.viewmodel.RegistroViewModel
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.viewmodel.RegistroViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var registroViewModel: RegistroViewModel
    private lateinit var registroAdapter: RegistroAdapter // Necesaria para el RecyclerView

    // Suponiendo que tienes un RecyclerView (id: recycler_view_registros)
    // y un FAB (id: fab_add) en tu layout activity_main.xml

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. INYECCIÓN DE DEPENDENCIAS (usando MiApp y Factory)
        val app = application as MiApp
        val factory = RegistroViewModelFactory(app.registroRepository)
        registroViewModel = ViewModelProvider(this, factory).get(RegistroViewModel::class.java)

        // 2. CONFIGURACIÓN DEL RECYCLERVIEW
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_registros)

        // El Adapter gestiona la visualización de los datos y el click
        registroAdapter = RegistroAdapter { registro ->
            // Manejar click en elemento (CRUD: Leer / Navegar a Actualizar)
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("REGISTRO_ID", registro.id)
            startActivity(intent)
        }

        recyclerView.adapter = registroAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. OBSERVACIÓN DE DATOS (CRUD: Leer - 2 puntos)
        registroViewModel.allRegistros.observe(this, Observer { registros ->
            // Actualiza la lista en el adaptador cada vez que la BD cambie
            registros?.let { registroAdapter.submitList(it) }
        })

        // 4. IMPLEMENTACIÓN DE SWIPE PARA BORRAR (CRUD: Borrar - 2 puntos)
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val registroToDelete = registroAdapter.getRegistroAt(viewHolder.adapterPosition)
                registroViewModel.delete(registroToDelete)
                Toast.makeText(this@MainActivity, "Registro de ${registroToDelete.nombre} eliminado", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // 5. BOTÓN PARA AÑADIR NUEVO REGISTRO (CRUD: Crear - 2 puntos)
        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener {
            // Navegar a DetailActivity o a una Activity de Creación
            val intent = Intent(this, DetailActivity::class.java)
            // Se puede enviar un ID nulo o -1 para indicar que es una creación nueva
            startActivity(intent)
        }
    }
}