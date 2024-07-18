package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class info_bateria : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info_bateria)
        val btnseguir = findViewById<ImageButton>(R.id.imageButtoninfoseguir)
        val btnstras = findViewById<ImageButton>(R.id.imageButtoninfoatras)

        btnseguir.setOnClickListener {
            navigateToSegir()
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }

    }
    private fun navigateToSegir() {
        val intent = Intent(this, materiales_bateria::class.java)
        startActivity(intent)}
    private fun navigateToatras() {
        val intent = Intent(this, mis_baterias::class.java)
        startActivity(intent)}
}