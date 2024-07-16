package com.proyecto.senes.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.senes.R
import com.proyecto.senes.modelo.Participante

class AdapterParticipante:
    RecyclerView.Adapter<AdapterParticipante.ViewHolder>() {

        private var listaparticipantes: List<Participante> = ArrayList()

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val nombre : TextView = itemView.findViewById(R.id.textViewNombrepart)
        val apellido : TextView = itemView.findViewById(R.id.textViewApellidopart)
        val sexo : TextView = itemView.findViewById(R.id.textViewSexopart)
        val nacimiento : TextView = itemView.findViewById(R.id.textViewNacimientopart)
        val patologia : TextView = itemView.findViewById(R.id.textViewPatologiapart)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterParticipante.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.participantebat,
            parent, false)
        return  ViewHolder(view)
    }
    override fun onBindViewHolder(holder: AdapterParticipante.ViewHolder, position: Int) {
        val participante = listaparticipantes[position]
        holder.nombre.text = participante.nombres
        holder.apellido.text = participante.apellidos
        holder.sexo.text = participante.sexo
        holder.nacimiento.text = participante.nacimiento
        holder.patologia.text = participante.patologia
    }
    override fun getItemCount(): Int {
        return listaparticipantes.size
    }
}