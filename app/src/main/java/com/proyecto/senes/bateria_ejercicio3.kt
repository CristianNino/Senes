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
            val puntuacion1 =binding.editTexta5.text.toString().trim()
            val puntuacion2 =binding.editTexta6.text.toString().trim()

            if(puntuacion1.isEmpty()){
                binding.editTexta5.error = "Ingrese la puntuacion"
                binding.editTexta5.requestFocus()
            }else if (puntuacion2.isEmpty()){
                binding.editTexta6.error = "Ingrese la puntuacion"
                binding.editTexta6.requestFocus()
            }else{
                val dato5 = Bundle()
                val dato6 = Bundle()

                dato5.putString("Keydato5", puntuacion1)
                dato6.putString("Keydato6", puntuacion2)

                val intent = Intent(this, resultado::class.java)
                intent.putExtras(dato5)
                intent.putExtras(dato6)
                startActivity(intent)
            }
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }
    }
    private fun navigateToatras() {
        val intent = Intent(this, bateria_ejercicio2::class.java)
        startActivity(intent)}
}



