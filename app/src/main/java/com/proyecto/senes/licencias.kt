package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class licencias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_licencias)

        val botonLicencia = findViewById<Button>(R.id.buttonlicencia)

        botonLicencia.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }

    }
}