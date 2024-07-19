package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.proyecto.senes.databinding.ActivityBateriaEjercicio3Binding

class bateria_ejercicio3 : AppCompatActivity() {

    private lateinit var binding: ActivityBateriaEjercicio3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBateriaEjercicio3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnstras = findViewById<ImageButton>(R.id.imageButtonatras3)
        val btnseguir = findViewById<ImageButton>(R.id.imageButtonseguir3)

        btnseguir.setOnClickListener {
            var puntuacion1 =binding.editTextfootup.text.toString().trim()
            val puntuacion2 =binding.editText2minutes.text.toString().trim()

            if(puntuacion1.isEmpty()){
                binding.editTextfootup.error = "Ingrese la puntuacion"
                binding.editTextfootup.requestFocus()
            }else if (puntuacion2.isEmpty()){
                binding.editText2minutes.error = "Ingrese la puntuacion"
                binding.editText2minutes.requestFocus()
            }else{
                navigateToSegir()
            }
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }
    }
    private fun navigateToSegir() {
        val intent = Intent(this, menu::class.java)
        startActivity(intent)}
    private fun navigateToatras() {
        val intent = Intent(this, bateria_ejercicio2::class.java)
        startActivity(intent)}
    }
