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
            var puntuacion1 =binding.editTexta3.text.toString().trim()
            val puntuacion2 =binding.editTexta4.text.toString().trim()

            if(puntuacion1.isEmpty()){
                binding.editTexta3.error = "Ingrese la puntuacion"
                binding.editTexta3.requestFocus()
            }else if (puntuacion2.isEmpty()){
                binding.editTexta4.error = "Ingrese la puntuacion"
                binding.editTexta4.requestFocus()
            }else{
                val dato3 = Bundle()
                val dato4 = Bundle()

                dato3.putString("Keydato3", puntuacion1.toString())
                dato4.putString("Keydato4", puntuacion2.toString())

                val intent3 = Intent(this, resultado::class.java)
                intent3.putExtras(dato3)
                intent3.putExtras(dato4)
                startActivity(intent3)

                val intent = Intent(this, bateria_ejercicio3::class.java)
                startActivity(intent)
            }
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }
    }
    private fun navigateToatras() {
        val intent = Intent(this, bateria_ejercicio1::class.java)
        startActivity(intent)}
}