package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.proyecto.senes.databinding.ActivityBateriaEjercicio2Binding
import com.proyecto.senes.databinding.ActivityBateriaEjercicio3Binding

class bateria_ejercicio2 : AppCompatActivity() {

    private lateinit var binding: ActivityBateriaEjercicio2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBateriaEjercicio2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnstras = findViewById<ImageButton>(R.id.imageButtonatras2)
        val btnseguir = findViewById<ImageButton>(R.id.imageButtonseguir2)

        btnseguir.setOnClickListener {
            var puntuacion1 =binding.editTextCharsit.text.toString().trim()
            val puntuacion2 =binding.editTextBackstrach.text.toString().trim()

            if(puntuacion1.isEmpty()){
                binding.editTextCharsit.error = "Ingrese la puntuacion"
                binding.editTextCharsit.requestFocus()
            }else if (puntuacion2.isEmpty()){
                binding.editTextBackstrach.error = "Ingrese la puntuacion"
                binding.editTextBackstrach.requestFocus()
            }else{
                navigateToSegir()
            }
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }
    }
    private fun navigateToSegir() {
        val intent = Intent(this, bateria_ejercicio3::class.java)
        startActivity(intent)}
    private fun navigateToatras() {
        val intent = Intent(this, bateria_ejercicio1::class.java)
        startActivity(intent)}
}