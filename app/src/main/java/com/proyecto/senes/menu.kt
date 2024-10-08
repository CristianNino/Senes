package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
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
        val btnprocedimientos = findViewById<ImageButton>(R.id.imageButton11)
        val btnnuevo = findViewById<ImageButton>(R.id.imageButtonnuevo)
        val btnparticipante = findViewById<ImageButton>(R.id.imageButtonparticipante)
        val btnbaterias = findViewById<ImageButton>(R.id.imageButtonbaterias)
        val btnExcel = findViewById<ImageButton>(R.id.imageButtonExcel)

        btnnuevo.setOnClickListener {
            navigateToNuevo()
        }
        btnparticipante.setOnClickListener {
            navigateToParticipante()
        }
        btnprocedimientos.setOnClickListener {
            val x = Intent(this, procedimientos::class.java)
            startActivity(x)
        }
        btnbaterias.setOnClickListener {
            navigateToBaterias()
        }
        btnExcel.setOnClickListener {
            val x = Intent(this, generarExcel::class.java)
            startActivity(x)
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
}