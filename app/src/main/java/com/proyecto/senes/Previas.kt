package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Previas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_previas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val previaVolver = findViewById<ImageButton>(R.id.imagePreviaVolver)
        val previaSeguir = findViewById<ImageButton>(R.id.imagePreviaSeguir)

        previaVolver.setOnClickListener {
            val intent = Intent(this, consideraciones::class.java)
            startActivity(intent)
        }
        previaSeguir.setOnClickListener {
            val intent = Intent(this, durante::class.java)
            startActivity(intent)
        }

    }
}