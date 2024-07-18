package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class bateria_ejercicio1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bateria_ejercicio1)
        val btnstras = findViewById<ImageButton>(R.id.imageButtonatra1)
        val btnseguir = findViewById<ImageButton>(R.id.imageButtonseguir1)

        btnseguir.setOnClickListener {
            navigateToSegir()
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }

    }
    private fun navigateToSegir() {
        val intent = Intent(this, bateria_ejercicio2::class.java)
        startActivity(intent)}
    private fun navigateToatras() {
        val intent = Intent(this, descripcion_bateria::class.java)
        startActivity(intent)}
}