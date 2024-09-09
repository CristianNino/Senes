package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class recomendaciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recomendaciones)

        asignarRecomendacion()


    }
    private fun asignarRecomendacion() {

        val database = FirebaseDatabase.getInstance().getReference()
        val textrecomendacion = findViewById<TextView>(R.id.textViewrecomendacion)
        val textespecificacion = findViewById<TextView>(R.id.textViewespecificacion)
        val sigue = findViewById<ImageButton>(R.id.imageButtonmsigue)

        database.child("Recomendaciones").child("Bajo_hombre").child("recomendacion").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val recomendacion = snapshot.getValue()
                    textrecomendacion.text = recomendacion.toString()
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
        )
        database.child("Recomendaciones").child("Bajo_hombre").child("especificacion").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val especificacion = snapshot.getValue()
                    textespecificacion.text = especificacion.toString()
                    /*esto es una prueba*/
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
        )

        sigue.setOnClickListener {
            val intent = Intent(this, ejercicios::class.java)
            startActivity(intent)
        }

        }


    }
