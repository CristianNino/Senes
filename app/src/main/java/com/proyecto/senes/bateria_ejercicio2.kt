package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
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
                try {
                    val number  = puntuacion1.toDouble()
                    val number1  = puntuacion2.toDouble()
                    val vr1 = intent.getStringExtra("key1")
                    val vr2 = intent.getStringExtra("key2")
                    val id = intent.getStringExtra("id5")

                    val intent3 = Intent(this, bateria_ejercicio3::class.java).apply {
                        putExtra("keyeje1", vr1)
                        putExtra("keyeje2", vr2)
                        putExtra("id6", id)
                        putExtra("key3", puntuacion1)
                        putExtra("key4", puntuacion2)
                    }
                    startActivity(intent3)
                }catch (e: NumberFormatException){
                    binding.editTexta3.error = "Valor Incorrecto"
                    binding.editTexta3.requestFocus()
                    binding.editTexta4.error = "Valor Incorrecto"
                    binding.editTexta4.requestFocus()
                }
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