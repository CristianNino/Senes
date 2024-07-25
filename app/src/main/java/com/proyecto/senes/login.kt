package com.proyecto.senes

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.proyecto.senes.databinding.ActivityLoginBinding

class login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.buttonlog.setOnClickListener {
            validarInfo()
        }

        binding.buttonregis.setOnClickListener {
            startActivity(Intent(applicationContext, registrousuario::class.java))
        }
    }

    private var correo = ""
    private var contraseña  = ""

    private fun validarInfo() {
        correo = binding.editTextTextEmailAddress.toString().trim()
        contraseña = binding.editTextTextPassword.toString().trim()

        if (correo.isEmpty()) {
            binding.editTextTextEmailAddress.error = "Ingrese correo"
            binding.editTextTextEmailAddress.requestFocus()
        /*}else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            binding.editTextTextEmailAddress.error ="Correo No válido"
            binding.editTextTextEmailAddress.requestFocus()*/
        }else if (contraseña.isEmpty()) {
            binding.editTextTextPassword.error ="Ingrese contraseña"
            binding.editTextTextPassword.requestFocus()
        }else{
            loginUsuario()

        }
    }
    private fun loginUsuario() {
            progressDialog.setMessage("Ingresando")
            progressDialog.show()

            firebaseAuth.signInWithEmailAndPassword(correo, contraseña)
                .addOnSuccessListener {
                    progressDialog.dismiss()
                    startActivity(Intent(this, menu::class.java))
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





}

