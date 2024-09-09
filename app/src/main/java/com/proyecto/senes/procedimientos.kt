package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class procedimientos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_procedimientos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val procedimientoSeguir = findViewById<ImageButton>(R.id.imageProcedimientoSeguir)
        val procedimientoVolver = findViewById<ImageButton>(R.id.imageProcedimientoVolver)

        procedimientoSeguir.setOnClickListener {
            val intent = Intent(this, consideraciones::class.java)
            startActivity(intent)
        }
        procedimientoVolver.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }

    }
}