package com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.ui.registros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.R
import com.example.a156708_acero_ruiz_zoe_152441_gonzalez_sanabria_danieljose.data.model.RegistroMedico


// Recibe una función de clic para manejar la navegación a DetailActivity
class RegistroAdapter(private val onItemClicked: (RegistroMedico) -> Unit) :
    ListAdapter<RegistroMedico, RegistroAdapter.RegistroViewHolder>(RegistrosComparator()) {

    // Define el ViewHolder que contendrá las vistas de cada elemento de la lista
    inner class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Suponiendo que estos IDs existen en tu layout list_item_registro.xml
        private val nombreTextView: TextView = itemView.findViewById(R.id.text_view_nombre_paciente)
        private val diagnosticoTextView: TextView = itemView.findViewById(R.id.text_view_diagnostico)
        private val fechaTextView: TextView = itemView.findViewById(R.id.text_view_fecha)

        fun bind(registro: RegistroMedico) {
            nombreTextView.text = registro.nombre
            diagnosticoTextView.text = registro.diagnostico
            fechaTextView.text = registro.fecha // Muestra la fecha de registro

            // Permite que el elemento completo sea clickeable
            itemView.setOnClickListener {
                onItemClicked(registro)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        // Infla el layout de cada elemento de la lista
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_registro, parent, false) // Asegúrate de crear este layout
        return RegistroViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        val currentRegistro = getItem(position)
        holder.bind(currentRegistro)
    }

    /**
     * Devuelve el registro en una posición específica.
     * Necesario para la funcionalidad de swipe/borrado en MainActivity.
     */
    fun getRegistroAt(position: Int): RegistroMedico {
        return getItem(position)
    }

    // Utilidad para calcular diferencias entre listas, mejorando el rendimiento del RecyclerView
    class RegistrosComparator : DiffUtil.ItemCallback<RegistroMedico>() {
        override fun areItemsTheSame(oldItem: RegistroMedico, newItem: RegistroMedico): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RegistroMedico, newItem: RegistroMedico): Boolean {
            return oldItem == newItem // Data class se encarga de comparar el contenido
        }
    }
}