package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.proyecto.senes.modelo.Puntuaciones
import com.proyecto.senes.modelo.Resultado

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
        val resultadoRes1TextView = findViewById<TextView>(R.id.textViewRes1)
        val resultadoRes2TextView = findViewById<TextView>(R.id.textViewRes2)
        val resultadoRes3TextView = findViewById<TextView>(R.id.textViewRes3)
        val resultadoRes4TextView = findViewById<TextView>(R.id.textViewRes4)
        val resultadoRes5TextView = findViewById<TextView>(R.id.textViewRes5)
        val resultadoRes6TextView = findViewById<TextView>(R.id.textViewRes6)
        val seguir = findViewById<ImageButton>(R.id.imageresultadoseguir)
        val atras = findViewById<ImageButton>(R.id.imageresultadoatras)

        database = FirebaseDatabase.getInstance().getReference()

        var age: Int? = null
        var gen : String? = null

        database.child("Participantes").child("$idP").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val edades = snapshot.child("edadp").getValue(String::class.java)
                    val generos = snapshot.child("sexo").getValue(String::class.java)

                    if(edades != null && generos != null){
                        age = edades.toIntOrNull()
                        gen = generos
                        Log.d("ConvertedData", "dato1: $age, dato2: $gen")
                        CalcularPuntuacion(age!!, gen!!, valorR1, valorR2, valorR3, valorR4, valorR5, valorR6)

                    }else{
                        Log.w("FirebaseData", "dato1 o dato2 es nulo")
                    }
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

        seguir.setOnClickListener {

            val puntua1 = resultadoRes1TextView.text.toString()
            val puntua2 = resultadoRes2TextView.text.toString()
            val puntua3 = resultadoRes3TextView.text.toString()
            val puntua4 = resultadoRes4TextView.text.toString()
            val puntua5 = resultadoRes5TextView.text.toString()
            val puntua6 = resultadoRes6TextView.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Puntuacion")

            var idN = database.child("Puntuacion").push().key
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

            val refere = FirebaseDatabase.getInstance().getReference("Resultados")
            val resultadoP =
                Resultado(idN, idP, puntua1, puntua2, puntua3, puntua4, puntua5, puntua6)

            refere.child(idN)
                .setValue(resultadoP)
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

            val intent = Intent(this, recomendaciones::class.java).apply {
                putExtra("key1", puntua1)
                putExtra("key2", puntua2)
                putExtra("key3", puntua3)
                putExtra("key4", puntua4)
                putExtra("key5", puntua5)
                putExtra("key6", puntua6)
                putExtra("Gen", gen)
            }
            startActivity(intent)
        }
        atras.setOnClickListener {
            val intent = Intent(this, bateria_ejercicio1::class.java)
            startActivity(intent)
        }
    }

    fun CalcularPuntuacion( ages:Int, gene: String, valorR1: Int, valorR2: Int, valorR3: Double, valorR4: Double, valorR5: Double, valorR6: Int){

        val resultadoR1TextView = findViewById<TextView>(R.id.textViewRes1)
        val resultadoR2TextView = findViewById<TextView>(R.id.textViewRes2)
        val resultadoR3TextView = findViewById<TextView>(R.id.textViewRes3)
        val resultadoR4TextView = findViewById<TextView>(R.id.textViewRes4)
        val resultadoR5TextView = findViewById<TextView>(R.id.textViewRes5)
        val resultadoR6TextView = findViewById<TextView>(R.id.textViewRes6)

        val resultadoR1 = when {
            gene == "Femenino" && ages in 60..64 && valorR1 in 11..18 -> "funcional"
            gene == "Femenino" && ages in 65..69 && valorR1 in 10..17 -> "funcional"
            gene == "Femenino" && ages in 70..74 && valorR1 in 9..16 -> "funcional"
            gene == "Femenino" && ages in 75..79 && valorR1 in 9..16 -> "funcional"
            gene == "Femenino" && ages in 80..84 && valorR1 in 8..15 -> "funcional"
            gene == "Femenino" && ages in 85..89 && valorR1 in 7..14 -> "funcional"
            gene == "Femenino" && ages in 90..94 && valorR1 in 3..12 -> "funcional"
            gene == "Masculino" && ages in 60..64 && valorR1 in 13..20 -> "funcional"
            gene == "Masculino" && ages in 65..69 && valorR1 in 11..19 -> "funcional"
            gene == "Masculino" && ages in 70..74 && valorR1 in 11..18 -> "funcional"
            gene == "Masculino" && ages in 75..79 && valorR1 in 10..18 -> "funcional"
            gene == "Masculino" && ages in 80..84 && valorR1 in 9..16 -> "funcional"
            gene == "Masculino" && ages in 85..89 && valorR1 in 7..15 -> "funcional"
            gene == "Masculino" && ages in 90..94 && valorR1 in 6..13 -> "funcional"
                else -> "no funcional"
        }

        val resultadoR2 = when {
            gene == "Femenino" && ages in 60..64 && valorR2 in 12..20 -> "funcional"
            gene == "Femenino" && ages in 65..69 && valorR2 in 13..19 -> "funcional"
            gene == "Femenino" && ages in 70..74 && valorR2 in 11..18 -> "funcional"
            gene == "Femenino" && ages in 75..79 && valorR2 in 10..18 -> "funcional"
            gene == "Femenino" && ages in 80..84 && valorR2 in 9..17 -> "funcional"
            gene == "Femenino" && ages in 85..89 && valorR2 in 9..16 -> "funcional"
            gene == "Femenino" && ages in 90..94 && valorR2 in 7..14 -> "funcional"
            gene == "Masculino" && ages in 60..64 && valorR2 in 15..23 -> "funcional"
            gene == "Masculino" && ages in 65..69 && valorR2 in 14..22 -> "funcional"
            gene == "Masculino" && ages in 70..74 && valorR2 in 13..22 -> "funcional"
            gene == "Masculino" && ages in 75..79 && valorR2 in 12..20 -> "funcional"
            gene == "Masculino" && ages in 80..84 && valorR2 in 12..20 -> "funcional"
            gene == "Masculino" && ages in 85..89 && valorR2 in 10..18 -> "funcional"
            gene == "Masculino" && ages in 90..94 && valorR2 in 9..15 -> "funcional"
            else -> "no funcional"
        }

        val resultadoR3 = when {
            gene == "Femenino" && ages in 60..64 && valorR3 in -0.4..5.0 -> "funcional"
            gene == "Femenino" && ages in 65..69 && valorR3 in -0.4..4.5 -> "funcional"
            gene == "Femenino" && ages in 70..74 && valorR3 in -0.9..4.1 -> "funcional"
            gene == "Femenino" && ages in 75..79 && valorR3 in -1.4..3.6 -> "funcional"
            gene == "Femenino" && ages in 80..84 && valorR3 in -1.9..3.1 -> "funcional"
            gene == "Femenino" && ages in 85..89 && valorR3 in -2.4..2.6 -> "funcional"
            gene == "Femenino" && ages in 90..94 && valorR3 in -4.4..1.1 -> "funcional"
            gene == "Masculino" && ages in 60..64 && valorR3 in -2.4..4.1 -> "funcional"
            gene == "Masculino" && ages in 65..69 && valorR3 in -2.9..3.1 -> "funcional"
            gene == "Masculino" && ages in 70..74 && valorR3 in -2.9..3.1 -> "funcional"
            gene == "Masculino" && ages in 75..79 && valorR3 in -3.9..2.1 -> "funcional"
            gene == "Masculino" && ages in 80..84 && valorR3 in -5.4..1.6 -> "funcional"
            gene == "Masculino" && ages in 85..89 && valorR3 in -5.4..0.4 -> "funcional"
            gene == "Masculino" && ages in 90..94 && valorR3 in -6.4..0.6 -> "funcional"
            else -> "no funcional"
        }

        val resultadoR4 = when {
            gene == "Femenino" && ages in 60..64 && valorR4 in -3.1..1.6 -> "funcional"
            gene == "Femenino" && ages in 65..69 && valorR4 in -3.6..1.6 -> "funcional"
            gene == "Femenino" && ages in 70..74 && valorR4 in -4.1..1.1 -> "funcional"
            gene == "Femenino" && ages in 75..79 && valorR4 in -5.1..0.6 -> "funcional"
            gene == "Femenino" && ages in 80..84 && valorR4 in -5.6..0.1 -> "funcional"
            gene == "Femenino" && ages in 85..89 && valorR4 in -7.1..-0.9 -> "funcional"
            gene == "Femenino" && ages in 90..94 && valorR4 in -8.1..-0.9 -> "funcional"
            gene == "Masculino" && ages in 60..64 && valorR4 in -6.4..0.1 -> "funcional"
            gene == "Masculino" && ages in 65..69 && valorR4 in -7.4..1.1 -> "funcional"
            gene == "Masculino" && ages in 70..74 && valorR4 in -7.9..1.1 -> "funcional"
            gene == "Masculino" && ages in 75..79 && valorR4 in -8.9..2.1 -> "funcional"
            gene == "Masculino" && ages in 80..84 && valorR4 in -9.4..2.1 -> "funcional"
            gene == "Masculino" && ages in 85..89 && valorR4 in -9.4..-3.1 -> "funcional"
            gene == "Masculino" && ages in 90..94 && valorR4 in -10.4..-4.1 -> "funcional"
            else -> "no funcional"
        }

        val resultadoR5 = when {
            gene == "Femenino" && ages in 60..64 && valorR5 in 4.5..5.9 -> "funcional"
            gene == "Femenino" && ages in 65..69 && valorR5 in 4.9..6.3 -> "funcional"
            gene == "Femenino" && ages in 70..74 && valorR5 in 5.0..7.0 -> "funcional"
            gene == "Femenino" && ages in 75..79 && valorR5 in 5.3..7.3 -> "funcional"
            gene == "Femenino" && ages in 80..84 && valorR5 in 5.8..8.6 -> "funcional"
            gene == "Femenino" && ages in 85..89 && valorR5 in 6.3..9.5 -> "funcional"
            gene == "Femenino" && ages in 90..94 && valorR5 in 7.4..11.4 -> "funcional"
            gene == "Masculino" && ages in 60..64 && valorR5 in 3.9..5.5 -> "funcional"
            gene == "Masculino" && ages in 65..69 && valorR5 in 4.4..5.8 -> "funcional"
            gene == "Masculino" && ages in 70..74 && valorR5 in 4.5..6.1 -> "funcional"
            gene == "Masculino" && ages in 75..79 && valorR5 in 4.7..7.1 -> "funcional"
            gene == "Masculino" && ages in 80..84 && valorR5 in 5.3..7.5 -> "funcional"
            gene == "Masculino" && ages in 85..89 && valorR5 in 5.6..8.8 -> "funcional"
            gene == "Masculino" && ages in 90..94 && valorR5 in 6.3..9.0 -> "funcional"
            else -> "no funcional"
        }

        val resultadoR6 = when {
            gene == "Femenino" && ages in 60..64 && valorR6 in 74..108 -> "funcional"
            gene == "Femenino" && ages in 65..69 && valorR6 in 72..108 -> "funcional"
            gene == "Femenino" && ages in 70..74 && valorR6 in 67..102 -> "funcional"
            gene == "Femenino" && ages in 75..79 && valorR6 in 67..101 -> "funcional"
            gene == "Femenino" && ages in 80..84 && valorR6 in 59..91 -> "funcional"
            gene == "Femenino" && ages in 85..89 && valorR6 in 54..86 -> "funcional"
            gene == "Femenino" && ages in 90..94 && valorR6 in 43..73 -> "funcional"
            gene == "Masculino" && ages in 60..64 && valorR6 in 86..116 -> "funcional"
            gene == "Masculino" && ages in 65..69 && valorR6 in 85..117 -> "funcional"
            gene == "Masculino" && ages in 70..74 && valorR6 in 79..111 -> "funcional"
            gene == "Masculino" && ages in 75..79 && valorR6 in 72..110 -> "funcional"
            gene == "Masculino" && ages in 80..84 && valorR6 in 70..104 -> "funcional"
            gene == "Masculino" && ages in 85..89 && valorR6 in 58..92 -> "funcional"
            gene == "Masculino" && ages in 90..94 && valorR6 in 51..87 -> "funcional"
            else -> "no funcional"
        }

        // Actualiza los TextViews con los resultados calculados
        resultadoR1TextView.text = resultadoR1
        resultadoR2TextView.text = resultadoR2
        resultadoR3TextView.text = resultadoR3
        resultadoR4TextView.text = resultadoR4
        resultadoR5TextView.text = resultadoR5
        resultadoR6TextView.text = resultadoR6
    }
}