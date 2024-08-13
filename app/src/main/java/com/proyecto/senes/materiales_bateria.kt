package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class materiales_bateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_materiales_bateria)
        val btnseguir = findViewById<ImageButton>(R.id.imageButtonmateseguir)
        val btnstras = findViewById<ImageButton>(R.id.imageButtonmateatras)

        btnseguir.setOnClickListener {
            navigateToSegir()
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }

    }
    private fun navigateToSegir() {
        val id = intent.getStringExtra("id2")
        val intent = Intent(this, descripcion_bateria::class.java).apply {
            putExtra("id3", id)
        }
        startActivity(intent)}
    private fun navigateToatras() {
        val intent = Intent(this, info_bateria::class.java)
        startActivity(intent)}
}