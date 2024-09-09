package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class durante_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_durante2)

        val btnSeguir = findViewById<ImageButton>(R.id.imageDurante2Seguir)
        val btnVolver = findViewById<ImageButton>(R.id.imageDurante2Volver)

        btnSeguir.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }
        btnVolver.setOnClickListener {
            val intent2 = Intent(this, durante::class.java)
            startActivity(intent2)
        }




    }
}