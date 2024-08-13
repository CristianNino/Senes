package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.proyecto.senes.modelo.Puntuaciones

class resultado : AppCompatActivity() {

    private lateinit var  database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)

        val valorR1 = intent.getStringExtra("keyejercicio1")
        val valorR2 = intent.getStringExtra("keyejercicio2")
        val valorR3 = intent.getStringExtra("keyejercicio3")
        val valorR4 = intent.getStringExtra("keyejercicio4")
        val valorR5 = intent.getStringExtra("key5")
        val valorR6 = intent.getStringExtra("key6")
        val idP = intent.getStringExtra("id7")


        val textActividad1 =findViewById<TextView>(R.id.textpa1)
        val textActividad2 =findViewById<TextView>(R.id.textViewarm)
        val textActividad3 =findViewById<TextView>(R.id.textpa3)
        val textActividad4 =findViewById<TextView>(R.id.textpa4)
        val textActividad5 =findViewById<TextView>(R.id.textpa5)
        val textActividad6 =findViewById<TextView>(R.id.textpa6)

        textActividad1.text = valorR1
        textActividad2.text = valorR2
        textActividad3.text = valorR3
        textActividad4.text = valorR4
        textActividad5.text = valorR5
        textActividad6.text = valorR6

        database = FirebaseDatabase.getInstance().getReference("Puntuacion")

        val idN = database.child("Puntuacion").push().key
        val puntuacionp = Puntuaciones(idN, idP, valorR1, valorR2, valorR3, valorR4, valorR5, valorR6)

         database.child(idN!!)
             .setValue(puntuacionp)
             .addOnSuccessListener {

             }
             .addOnFailureListener { e ->
                 Toast.makeText(
                     this,
                     "fallo el registro en BD debido a ${e.message}",
                     Toast.LENGTH_SHORT
                 ).show()
             }
    }

    /*private fun obtenerInfo() {
        val id1 = intent.getStringExtra("id7")
        val referencia = database.child("Participantes").child("$id1")

        referencia.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }*/
}