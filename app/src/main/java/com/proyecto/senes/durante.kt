package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class durante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_durante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val duranteSeguir = findViewById<ImageButton>(R.id.imageDuranteSeguir)
        val duranteVolver = findViewById<ImageButton>(R.id.imageDuranteVolver)

        duranteSeguir.setOnClickListener {
            val intent = Intent(this, durante_2::class.java)
            startActivity(intent)
        }
        duranteVolver.setOnClickListener {
            val intent2 = Intent(this,  Previas::class.java)
            startActivity(intent2)
        }

    }
}