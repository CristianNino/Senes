package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class consideraciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_consideraciones)

        val consideracionSeguir = findViewById<ImageButton>(R.id.imageConsideracionSeguir)
        val consideracionVolver = findViewById<ImageButton>(R.id.imageConsideracionVolver)

        consideracionSeguir.setOnClickListener {
            val intent = Intent(this, Previas::class.java)
            startActivity(intent)
        }
        consideracionVolver.setOnClickListener {
            val intent2 = Intent(this,  procedimientos::class.java)
            startActivity(intent2)
        }

    }
}