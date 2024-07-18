package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class descripcion_bateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_descripcion_bateria)
        val btnseguir = findViewById<ImageButton>(R.id.imageButtondescripseguir)
        val btnstras = findViewById<ImageButton>(R.id.imageButtondescripatras)

        btnseguir.setOnClickListener {
            navigateToSegir()
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }

    }
    private fun navigateToSegir() {
        val intent = Intent(this, bateria_ejercicio1::class.java)
        startActivity(intent)}
    private fun navigateToatras() {
        val intent = Intent(this, materiales_bateria::class.java)
        startActivity(intent)}
}