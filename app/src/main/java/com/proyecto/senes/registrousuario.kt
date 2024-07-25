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
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.proyecto.senes.databinding.ActivityRegistrousuarioBinding
import org.intellij.lang.annotations.Pattern

class registrousuario : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrousuarioBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrousuarioBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrousuario)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.buttonregis.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            validarInformacion()
        }
    }
    private var Rcorreo = ""
    private var Rcontraseña = ""
    private var Rconfirmar = ""

    private fun validarInformacion() {

        Rcorreo = binding.TextRcorreo.text.toString().trim()
        Rcontraseña = binding.TextRcontraseA.text.toString().trim()
        Rconfirmar = binding.TextRconfirmar.text.toString().trim()

        if (Rcorreo.isEmpty()) {
            binding.TextRcorreo.error = "Ingrese correo"
            binding.TextRcorreo.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(Rcorreo).matches()) {
            binding.TextRcorreo.error = "Correo no válido"
            binding.TextRcorreo.requestFocus()
        } else if (Rcontraseña.isEmpty()) {
            binding.TextRcontraseA.error = "Ingrese contraseña"
            binding.TextRcontraseA.requestFocus()
        }else if (Rcontraseña.length < 6) {
            binding.TextRcontraseA.error = "Necesita 6 o mas caracteres"
            binding.TextRcontraseA.requestFocus()
        }else if (Rconfirmar.isEmpty()) {
            binding.TextRconfirmar.error = "Confirme contraseña"
            binding.TextRconfirmar.requestFocus()
        } else if (Rcontraseña != Rconfirmar) {
            binding.TextRconfirmar.error = "No concide la contradeña "
            binding.TextRconfirmar.requestFocus()
        } else {
            RegistrarUsuario()
        }
    }
    private fun RegistrarUsuario() {
        progressDialog.setMessage("Creando usuario")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(Rcorreo, Rcontraseña)
            .addOnSuccessListener(this) {
                insertarInfoBD()
            }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        this,
                        "Fallo el registro debibo a ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

    private fun insertarInfoBD() {
        progressDialog.setMessage("Guardando Información...")

        val uidBD = firebaseAuth.uid
        val correoBD = Rcorreo
        val contrasenaBD = Rcontraseña

        val datosUsuario = HashMap<String, Any>()
        datosUsuario["uid"] = "$uidBD"
        datosUsuario["Rcorreo"] = "$correoBD"
        datosUsuario["Contrasena"] = "$contrasenaBD"

        val references = FirebaseDatabase.getInstance().getReference("Usuarios")
        references.child(uidBD!!)
            .setValue(datosUsuario)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, login_usuario::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(
                    this,
                    "Fallo el registro en BD debibo a ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                }
        }
    }
