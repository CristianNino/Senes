package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        val reco1 = intent.getStringExtra("key1")
        val reco2 = intent.getStringExtra("key2")
        val reco3 = intent.getStringExtra("key3")
        val reco4 = intent.getStringExtra("key4")
        val reco5 = intent.getStringExtra("key5")
        val reco6 = intent.getStringExtra("key6")
        val gener = intent.getStringExtra("Gen")

        asignarRecomendacion(gener ?: "", reco1 ?: "")

    }

    private fun asignarRecomendacion(gen: String, reco: String) {

        val database = FirebaseDatabase.getInstance().getReference("Recomendaciones")

        val textrecomendacion = findViewById<TextView>(R.id.textViewrecomendacion)
        val textespecificacion = findViewById<TextView>(R.id.textViewespecificacion)
        val sigue = findViewById<ImageButton>(R.id.imageButtonmsigue)

        when {
            reco == "funcional" && gen == "Femenino" -> {
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val recomendacion =
                            snapshot.child("Medio_mujer").child("recomendacion").getValue()
                        val especificacion =
                            snapshot.child("Medio_mujer").child("especificacion").getValue()
                        textrecomendacion.text = recomendacion.toString()
                        textespecificacion.text = especificacion.toString()

                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w("Firebase", "Error al leer el valor.", error.toException())
                    }
                })
            }

            reco == "funcional" && gen == "Masculino" -> {
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val recomendacion =
                            snapshot.child("Medio_hombre").child("recomendacion").getValue()
                        val especificacion =
                            snapshot.child("Medio_hombre").child("especificacion").getValue()
                        textrecomendacion.text = recomendacion.toString()
                        textespecificacion.text = especificacion.toString()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w("Firebase", "Error al leer el valor.", error.toException())
                    }
                })
            }

            reco == "no funcional" && gen == "Femenino" -> {
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val recomendacion =
                            snapshot.child("Bajo_mujer").child("recomendacion").getValue()
                        val especificacion =
                            snapshot.child("Bajo_mujer").child("especificacion").getValue()
                        textrecomendacion.text = recomendacion.toString()
                        textespecificacion.text = especificacion.toString()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w("Firebase", "Error al leer el valor.", error.toException())
                    }
                })
            }

            reco == "no funcional" && gen == "Masculino" -> {
                database.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val recomendacion =
                            snapshot.child("Bajo_hombre").child("recomendacion").getValue()
                        val especificacion =
                            snapshot.child("Bajo_hombre").child("especificacion").getValue()
                        textrecomendacion.text = recomendacion.toString()
                        textespecificacion.text = especificacion.toString()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w("Firebase", "Error al leer el valor.", error.toException())
                    }
                })
            }

            else -> {
                Log.w("Firebase", "el when no funciono correctamente.",)
            }
        }
        sigue.setOnClickListener {
            val intent = Intent(this, ejercicios::class.java)
            startActivity(intent)
        }
    }
}


