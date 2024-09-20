package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
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

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resultado)


        val valorR1 = intent.getStringExtra("keyejercicio1")?.toIntOrNull() ?: 0
        val valorR2 = intent.getStringExtra("keyejercicio2")?.toIntOrNull() ?: 0
        val valorR3 = intent.getStringExtra("keyejercicio3")?.toDoubleOrNull() ?: 0.0
        val valorR4 = intent.getStringExtra("keyejercicio4")?.toDoubleOrNull() ?: 0.0
        val valorR5 = intent.getStringExtra("key5")?.toDoubleOrNull() ?: 0.0
        val valorR6 = intent.getStringExtra("key6")?.toIntOrNull() ?: 0
        val idP = intent.getStringExtra("id7")

        val textActividad1 = findViewById<TextView>(R.id.textpa1)
        val textActividad2 = findViewById<TextView>(R.id.textViewarm)
        val textActividad3 = findViewById<TextView>(R.id.textpa3)
        val textActividad4 = findViewById<TextView>(R.id.textpa4)
        val textActividad5 = findViewById<TextView>(R.id.textpa5)
        val textActividad6 = findViewById<TextView>(R.id.textpa6)
        val resultadoR1TextView = findViewById<TextView>(R.id.textViewRes1)
        val resultadoR2TextView = findViewById<TextView>(R.id.textViewRes2)
        val resultadoR3TextView = findViewById<TextView>(R.id.textViewRes3)
        val resultadoR4TextView = findViewById<TextView>(R.id.textViewRes4)
        val resultadoR5TextView = findViewById<TextView>(R.id.textViewRes5)
        val resultadoR6TextView = findViewById<TextView>(R.id.textViewRes6)
        val seguir = findViewById<ImageButton>(R.id.imageresultadoseguir)
        val atras = findViewById<ImageButton>(R.id.imageresultadoatras)

        database = FirebaseDatabase.getInstance().getReference()

        var age: Int? = null
        var gen: String? = null

        database.child("Participantes").child("$idP").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val edades = snapshot.child("edadp").getValue(String::class.java)
                    age = edades?.toIntOrNull()
                    gen = snapshot.child("sexo").getValue(String::class.java)
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
        )



        textActividad1.text = valorR1.toString()
        textActividad2.text = valorR2.toString()
        textActividad3.text = valorR3.toString()
        textActividad4.text = valorR4.toString()
        textActividad5.text = valorR5.toString()
        textActividad6.text = valorR6.toString()

        // Calcula el resultado para cada puntuación

        val resultadoR1 = when {
            gen == "Femenino" && age in 60..64 && valorR1 in 11..18 -> "funcional"
            gen == "Femenino" && age in 65..69 && valorR1 in 10..17 -> "funcional"
            gen == "Femenino" && age in 70..74 && valorR1 in 9..16 -> "funcional"
            gen == "Femenino" && age in 75..79 && valorR1 in 9..16 -> "funcional"
            gen == "Femenino" && age in 80..84 && valorR1 in 8..15 -> "funcional"
            gen == "Femenino" && age in 85..89 && valorR1 in 7..14 -> "funcional"
            gen == "Femenino" && age in 90..94 && valorR1 in 3..12 -> "funcional"
            else -> "no funcional"
        }

        val resultadoR2 = when {
            gen == "Femenino" && age in 60..64 && valorR2 in 12..20 -> "funcional"
            gen == "Femenino" && age in 65..69 && valorR2 in 13..19 -> "funcional"
            gen == "Femenino" && age in 70..74 && valorR2 in 11..18 -> "funcional"
            gen == "Femenino" && age in 75..79 && valorR2 in 10..18 -> "funcional"
            gen == "Femenino" && age in 80..84 && valorR2 in 9..17 -> "funcional"
            gen == "Femenino" && age in 85..89 && valorR2 in 9..16 -> "funcional"
            gen == "Femenino" && age in 90..94 && valorR2 in 7..14 -> "funcional"
            else -> "no funcional"
        }

        val resultadoR3 = when {
            gen == "Femenino" && age in 60..64 && valorR3 in -0.4..5.0 -> "funcional"
            gen == "Femenino" && age in 65..69 && valorR3 in -0.4..4.5 -> "funcional"
            gen == "Femenino" && age in 70..74 && valorR3 in -0.9..4.1 -> "funcional"
            gen == "Femenino" && age in 75..79 && valorR3 in -1.4..3.6 -> "funcional"
            gen == "Femenino" && age in 80..84 && valorR3 in -1.9..3.1 -> "funcional"
            gen == "Femenino" && age in 85..89 && valorR3 in -2.4..2.6 -> "funcional"
            gen == "Femenino" && age in 90..94 && valorR3 in -4.4..1.1 -> "funcional"
            else -> "no funcional"
        }

        val resultadoR4 = when {
            gen == "Femenino" && age in 60..64 && valorR4 in -3.1..1.6 -> "funcional"
            gen == "Femenino" && age in 65..69 && valorR4 in -3.6..1.6 -> "funcional"
            gen == "Femenino" && age in 70..74 && valorR4 in -4.1..1.1 -> "funcional"
            gen == "Femenino" && age in 75..79 && valorR4 in -5.1..0.6 -> "funcional"
            gen == "Femenino" && age in 80..84 && valorR4 in -5.6..0.1 -> "funcional"
            gen == "Femenino" && age in 85..89 && valorR4 in -7.1..-0.9 -> "funcional"
            gen == "Femenino" && age in 90..94 && valorR4 in -8.1..-0.9 -> "funcional"
            else -> "no funcional"
        }

        val resultadoR5 = when {
            gen == "Femenino" && age in 60..64 && valorR5 in 4.5..5.9 -> "funcional"
            gen == "Femenino" && age in 65..69 && valorR5 in 4.9..6.3 -> "funcional"
            gen == "Femenino" && age in 70..74 && valorR5 in 5.0..7.0 -> "funcional"
            gen == "Femenino" && age in 75..79 && valorR5 in 5.3..7.3 -> "funcional"
            gen == "Femenino" && age in 80..84 && valorR5 in 5.8..8.6 -> "funcional"
            gen == "Femenino" && age in 85..89 && valorR5 in 6.3..9.5 -> "funcional"
            gen == "Femenino" && age in 90..94 && valorR5 in 7.4..11.4 -> "funcional"
            else -> "no funcional"
        }

        val resultadoR6 = when {
            gen == "Femenino" && age in 60..64 && valorR6 in 74..108 -> "funcional"
            gen == "Femenino" && age in 65..69 && valorR6 in 72..108 -> "funcional"
            gen == "Femenino" && age in 70..74 && valorR6 in 67..102 -> "funcional"
            gen == "Femenino" && age in 75..79 && valorR6 in 67..101 -> "funcional"
            gen == "Femenino" && age in 80..84 && valorR6 in 59..91 -> "funcional"
            gen == "Femenino" && age in 85..89 && valorR6 in 54..86 -> "funcional"
            gen == "Femenino" && age in 90..94 && valorR6 in 43..73 -> "funcional"
            else -> "no funcional"
        }

        // Actualiza los TextViews con los resultados calculados
        resultadoR1TextView.text = resultadoR1
        resultadoR2TextView.text = resultadoR2
        resultadoR3TextView.text = resultadoR3
        resultadoR4TextView.text = resultadoR4
        resultadoR5TextView.text = resultadoR5
        resultadoR6TextView.text = resultadoR6



        seguir.setOnClickListener {
            database = FirebaseDatabase.getInstance().getReference("Puntuacion")

            val idN = database.child("Puntuacion").push().key
            val puntuacionP =
                Puntuaciones(idN, idP, valorR1.toString(), valorR2.toString(), valorR3.toString(), valorR4.toString(), valorR5.toString(), valorR6.toString())

            database.child(idN!!)
                .setValue(puntuacionP)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "el registro ha sido cargado",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        "fallo el registo en BD debido a ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            val intent = Intent(this, recomendaciones::class.java)
            startActivity(intent)
        }
        atras.setOnClickListener {
            val intent = Intent(this, bateria_ejercicio1::class.java)
            startActivity(intent)
        }
    }
}