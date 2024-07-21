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

            var puntuacion1 =binding.editTexta1.text.toString().trim()
            val puntuacion2 =binding.editTexta2.text.trim()

            if(puntuacion1.isEmpty()){
                binding.editTexta1.error = "Ingrese la puntuacion"
                binding.editTexta1.requestFocus()
            }else if (puntuacion2.isEmpty()){
                binding.editTexta2.error = "Ingrese la puntuacion"
                binding.editTexta2.requestFocus()
            }else{

                val dato1 = Bundle()
                val dato2 = Bundle()

                dato1.putString("Keydato1", puntuacion1)
                dato2.putString("Keydato2", puntuacion2.toString())

                val intent = Intent(this, bateria_ejercicio2::class.java)
                startActivity(intent)

                val intent2 = Intent(this, resultado::class.java)
                intent2.putExtras(dato1)
                intent2.putExtras(dato2)
            }
        }
        btnstras.setOnClickListener {
            navigateToatras()
        }

    }
    private fun navigateToatras() {
        val intent = Intent(this, descripcion_bateria::class.java)
        startActivity(intent)}
}

