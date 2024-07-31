package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class mis_baterias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mis_baterias)

        val btnatras = findViewById<ImageButton>(R.id.imageButtonatras)
        val select = findViewById<TextView>(R.id.textViewsenior)

        select.setOnClickListener{
            SeguirBateria()
        }


        btnatras.setOnClickListener {
            navigateToatras()
        }
    }

    private fun SeguirBateria() {
        val intent = Intent(this, select_participante::class.java)
        startActivity(intent) }
    private fun navigateToatras() {
        val intent = Intent(this, menu::class.java)
        startActivity(intent)}
}