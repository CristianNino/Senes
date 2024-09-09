package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ejercicio_fuerza : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio_fuerza)

        val volver = findViewById<ImageButton>(R.id.imageButtonVolverFuerza)

        volver.setOnClickListener {
            Volver()
        }

    }
    private fun Volver (){
        val intent = Intent(this, ejercicios::class.java)
        startActivity(intent)
    }
}