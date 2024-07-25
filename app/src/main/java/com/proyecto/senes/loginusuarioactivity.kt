package com.proyecto.senes

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.proyecto.senes.databinding.ActivityLoginBinding



class loginusuarioactivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this )
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.buttonlog.setOnClickListener {
            validarInfo()

        }

        binding.buttonregis.setOnClickListener {
            startActivity(Intent(applicationContext, login::class.java))
        }

        val registrar = findViewById<MaterialButton>(R.id.botoningresarloginuser)

        registrar.setOnClickListener{
            NavigateToMenu()
        }
    }

    private var correologinuser = ""
    private var contrasenaloginuser  = ""
    private fun validarInfo() {
        correologinuser = binding.editTextTextEmailAddress.toString().trim()
        contrasenaloginuser = binding.editTextTextPassword.toString().trim()

        if (correologinuser.isEmpty()) {
            binding.editTextTextEmailAddress.error = "Ingrese correo"
            binding.editTextTextEmailAddress.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(correologinuser).matches()) {
            binding.editTextTextEmailAddress.error ="Correo No válido"
            binding.editTextTextEmailAddress.requestFocus()
        }else if (contrasenaloginuser.isEmpty()) {
            binding.editTextTextPassword.error ="Ingrese contraseña"
            binding.editTextTextPassword.requestFocus()
        }else{
            loginUsuario()

        }

    }

    private fun loginUsuario() {
        progressDialog.setMessage("Ingresando")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(correologinuser, contrasenaloginuser)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, login::class.java))
                finishAffinity()
                Toast.makeText(
                    this,
                    "Bienvenido(a)",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(
                    this,
                    "No se pudo iniciar sesión debido a ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }


    }

    private fun NavigateToMenu() {
        val intent = Intent(this, menu::class.java)
        startActivity(intent)
    }
}