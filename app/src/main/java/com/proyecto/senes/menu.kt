package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnnuevo = findViewById<ImageButton>(R.id.imageButtonnuevo)
        val btnparticipante = findViewById<ImageButton>(R.id.imageButtonparticipante)
        val btnbaterias = findViewById<ImageButton>(R.id.imageButtonbaterias)

        btnnuevo.setOnClickListener {
            navigateToNuevo()
        }
        btnparticipante.setOnClickListener {
            navigateToParticipante()
        }
        btnbaterias.setOnClickListener {
            navigateToBaterias()
        }
    }
    private fun navigateToNuevo() {
        val intent = Intent(this, registro_participante::class.java)
        startActivity(intent)}
    private fun navigateToParticipante() {
        val intent = Intent(this, miparticipante::class.java)
        startActivity(intent)}
    private fun navigateToBaterias() {
        val intent = Intent(this, mis_baterias::class.java)
        startActivity(intent)}
/*"Revision"*/
}