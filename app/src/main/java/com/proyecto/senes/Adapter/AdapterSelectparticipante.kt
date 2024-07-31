package com.proyecto.senes.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.proyecto.senes.R
import com.proyecto.senes.info_bateria
import com.proyecto.senes.modelo.Participante

class AdapterSelectparticipante (private val listaselectparticipantes: ArrayList<Participante>):
    RecyclerView.Adapter<AdapterSelectparticipante.ViewHolder>() {

    private lateinit var database : DatabaseReference

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val nombre : TextView = itemView.findViewById(R.id.textViewselectNombre)
        val apellido : TextView = itemView.findViewById(R.id.textViewselectApellido)
        val boton : Button = itemView.findViewById(R.id.buttonselect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewselect = LayoutInflater.from(parent.context).inflate(R.layout.card_select,
            parent, false)
        return  ViewHolder(viewselect)
    }
    override fun getItemCount(): Int {
        return listaselectparticipantes.size}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val participante = listaselectparticipantes[position]

        holder.nombre.text = participante.nombres
        holder.apellido.text = participante.apellidos


        val nombre = holder.nombre.text.toString()


        holder.boton.setOnClickListener {

            val context = holder.itemView.context


            database = FirebaseDatabase.getInstance().reference

            val nuevodato = HashMap<String, Any>()
            nuevodato["Bateria"] = "senior fitness test"

            obtenerId(nombre){ userId ->
                if (userId != null) {
                    database.child("Participantes").child("$userId").updateChildren(nuevodato)
                        .addOnSuccessListener {

                            val intent = Intent(context, info_bateria::class.java)
                            context.startActivity(intent)
                            Toast.makeText(context, "Dato agregado", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "Error al agregar el dato", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(context, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun obtenerId (nombre: String, callback: (String?) -> Unit){

        val query = database.child("Participantes").orderByChild("nombres").equalTo(nombre)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val userId = userSnapshot.key
                        callback(userId)
                        return
                    }
                }
                callback(null)
            }
            override fun onCancelled(error: DatabaseError) {
                callback(null)
            }
        })
    }

}
