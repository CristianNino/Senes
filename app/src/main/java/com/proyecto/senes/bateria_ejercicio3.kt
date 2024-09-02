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

                val vr1 = intent.getStringExtra("keyeje1")
                val vr2 = intent.getStringExtra("keyeje2")
                val vr3 = intent.getStringExtra("key3")
                val vr4 = intent.getStringExtra("key4")
                val id = intent.getStringExtra("id6")

                val intent = Intent(this, resultado::class.java).apply {
                    putExtra("keyejercicio1", vr1)
                    putExtra("keyejercicio2", vr2)
                    putExtra("keyejercicio3", vr3)
                    putExtra("keyejercicio4", vr4)
                    putExtra("id7", id)
                    putExtra("key5", puntuacion1)
                    putExtra("key6", puntuacion2)
                }
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




