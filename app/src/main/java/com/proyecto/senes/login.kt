package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnlog = findViewById<Button>(R.id.buttonlog)
        val btnregis = findViewById<Button>(R.id.buttonregis)
        btnlog.setOnClickListener {
            navigateToMenu()
        }
        btnregis.setOnClickListener{
            NavigateToRegis()
        }
    }

    private fun NavigateToRegis() {
        val intent = Intent(this, loginusuarioactivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMenu() {
        val intent = Intent(this, menu::class.java)
        startActivity(intent)
    }
}