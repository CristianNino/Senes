package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.proyecto.senes.databinding.ActivityBateriaEjercicio1Binding
import com.proyecto.senes.databinding.ActivityBateriaEjercicio3Binding
import com.proyecto.senes.databinding.ActivityRegistroParticipanteBinding

class bateria_ejercicio1 : AppCompatActivity() {

    private lateinit var binding: ActivityBateriaEjercicio1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBateriaEjercicio1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnstras = findViewById<ImageButton>(R.id.imageButtonatra1)
        val btnseguir = findViewById<ImageButton>(R.id.imageButtonseguir1)

        btnseguir.setOnClickListener {

            var puntuacion1 =binding.editTextChairstand.text.toString().trim()
            val puntuacion2 =binding.editTextArmcurl.text.trim()

            if(puntuacion1.isEmpty()){
                binding.editTextChairstand.error = "Ingrese la puntuacion"
                binding.editTextChairstand.requestFocus()
            }else if (puntuacion2.isEmpty()){
                binding.editTextArmcurl.error = "Ingrese la puntuacion"
                binding.editTextArmcurl.requestFocus()
            }else{
                navigateToSegir()
            }
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }

    }
    private fun navigateToSegir() {
        val intent = Intent(this, bateria_ejercicio2::class.java)
        startActivity(intent)}
    private fun navigateToatras() {
        val intent = Intent(this, descripcion_bateria::class.java)
        startActivity(intent)}
}