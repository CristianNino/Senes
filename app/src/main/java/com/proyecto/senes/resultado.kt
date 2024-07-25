package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)



        val valorR1 = intent.getStringExtra("keyejercicio1")
        val valorR2 = intent.getStringExtra("keyejercicio2")
        val valorR3 = intent.getStringExtra("keyejercicio3")
        val valorR4 = intent.getStringExtra("keyejercicio4")
        val valorR5 = intent.getStringExtra("key5")
        val valorR6 = intent.getStringExtra("key6")

        val textActividad1 =findViewById<TextView>(R.id.textpa1)
        val textActividad2 =findViewById<TextView>(R.id.textViewarm)
        val textActividad3 =findViewById<TextView>(R.id.textpa3)
        val textActividad4 =findViewById<TextView>(R.id.textpa4)
        val textActividad5 =findViewById<TextView>(R.id.textpa5)
        val textActividad6 =findViewById<TextView>(R.id.textpa6)

        textActividad1.text = valorR1
        textActividad2.text = valorR2
        textActividad3.text = valorR3
        textActividad4.text = valorR4
        textActividad5.text = valorR5
        textActividad6.text = valorR6



    }
}