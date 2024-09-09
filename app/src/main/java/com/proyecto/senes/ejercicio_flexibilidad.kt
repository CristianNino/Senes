package com.proyecto.senes

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ejercicio_flexibilidad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ejercicio_flexibilidad)

        val volver = findViewById<ImageButton>(R.id.imageButtonVolverFlexibilidad)

        volver.setOnClickListener {
            Volver()
        }

    }
    private fun Volver (){
        val intent = Intent(this, ejercicios::class.java)
        startActivity(intent)
    }
}