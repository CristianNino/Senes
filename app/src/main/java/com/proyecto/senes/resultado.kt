package com.proyecto.senes

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

        val textActividad1 =findViewById<TextView>(R.id.textpa1)
        val textActividad2 =findViewById<TextView>(R.id.textpa2)
        val textActividad3 =findViewById<TextView>(R.id.textpa3)
        val textActividad4 =findViewById<TextView>(R.id.textpa4)
        val textActividad5 =findViewById<TextView>(R.id.textpa5)
        val textActividad6 =findViewById<TextView>(R.id.textpa6)

        val rdato1 = Bundle(intent.extras)
        val rdato2 = Bundle(intent.extras)
        val rdato3 = Bundle(intent.extras)
        val rdato4 = Bundle(intent.extras)
        val rdato5 = Bundle(intent.extras)
        val rdato6 = Bundle(intent.extras)

        val info1 = rdato1.getString("Keydato1")
        val info2 = rdato2.getString("Keydato2")
        val info3 = rdato3.getString("Keydato3")
        val info4 = rdato4.getString("Keydato4")
        val info5 = rdato5.getString("Keydato5")
        val info6 = rdato6.getString("Keydato6")

        textActividad1.text = info1
        textActividad2.text = info2
        textActividad3.text = info3
        textActividad4.text = info4
        textActividad5.text = info5
        textActividad6.text = info6


    }
}