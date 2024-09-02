package com.proyecto.senes

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.proyecto.senes.databinding.ActivityLoginUsuarioBinding

class login_usuario : AppCompatActivity() {

    private lateinit var binding: ActivityLoginUsuarioBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginUsuarioBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.buttonlogin.setOnClickListener{
            validarinfo()
        }
        binding.buttonregister.setOnClickListener{
            startActivity(Intent(applicationContext, registrousuario::class.java))
        }

    }

    private var email = ""
    private var pass = ""

    private fun validarinfo() {

        email = binding.Textemail.text.toString().trim()
        pass = binding.Textpass.text.toString().trim()

        if(email.isEmpty()){
            binding.Textemail.error = "Ingrese el correo"
            binding.Textemail.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.Textemail.error = "Correo no valido"
            binding.Textemail.requestFocus()
        }else if (pass.isEmpty()){
            binding.Textpass.error = "Ingrese la contraseña"
            binding.Textpass.requestFocus()
        }else{
            loginUsuario()
        }
    }

    private fun loginUsuario() {

        progressDialog.setMessage("Ingresando")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, licencias::class.java))
                finishAffinity()
                Toast.makeText( this,
                    "Bienvenido",
                    Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener {  e->
                progressDialog.dismiss()
                Toast.makeText(this,
                    "No se pudo iniciar sesion debido a ${e.message}",
                    Toast.LENGTH_SHORT)
                    .show()
            }
    }
}