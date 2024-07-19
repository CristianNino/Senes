package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class loginusuarioactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_usuario)

        val btnregis = findViewById<MaterialButton>(R.id.btnLoginU)

        btnregis.setOnClickListener{
            NavigateToMenu()
        }
    }

    private fun NavigateToMenu() {
        val intent = Intent(this, menu::class.java)
        startActivity(intent)
    }
}