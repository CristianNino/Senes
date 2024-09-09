package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ejercicios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicios)

        val fuerza = findViewById<ImageView>(R.id.imageViewFuerza)
        val equilibrio = findViewById<ImageView>(R.id.imageViewEquilibrio)
        val flexibilidad = findViewById<ImageView>(R.id.imageViewFlexibiidad)
        val cardiovascular = findViewById<ImageView>(R.id.imageViewCardiovascular)
        val volver = findViewById<ImageButton>(R.id.imageButtonVolver)

        fuerza.setOnClickListener {
            Fuerza()
        }
        flexibilidad.setOnClickListener {
            Flexibilidad()
        }
        equilibrio.setOnClickListener {
            Equilibrio()
        }
        cardiovascular.setOnClickListener {
            Cardiovascular()
        }
        volver.setOnClickListener {
            Volver()
        }

    }
    private fun Fuerza (){
        val intent = Intent(this, ejercicio_fuerza::class.java)
        startActivity(intent)
    }
    private fun Flexibilidad (){
        val intent = Intent(this, ejercicio_flexibilidad::class.java)
        startActivity(intent)
    }
    private fun Equilibrio (){
        val intent = Intent(this, ejercicio_equilibrio::class.java)
        startActivity(intent)
    }
    private fun Cardiovascular (){
        val intent = Intent(this, ejercicio_cardiovascular::class.java)
        startActivity(intent)
    }
    private fun Volver (){
        val intent = Intent(this, recomendaciones::class.java)
        startActivity(intent)
    }



}