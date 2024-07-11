package com.proyecto.senes

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentDialog
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.proyecto.senes.databinding.ActivityRegistroParticipanteBinding

class registro_participante : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroParticipanteBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroParticipanteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.buttonRegistroParticipante.setOnClickListener{
            ValidarInformacion()
        }
    }

    private var nombres = ""
    private var apellidos = ""
    private var cedula = ""
    private var sexo = ""
    private var nacimiento = ""
    private var patologia = ""

    private fun ValidarInformacion(){
        nombres = binding.editTextNombres.text.toString().trim()
        apellidos = binding.editTextApellidos.text.toString().trim()
        cedula = binding.editTextCedula.text.toString().trim()
        sexo = binding.editTextSexo.text.toString().trim()
        nacimiento = binding.editTextNacimiento.text.toString().trim()
        patologia = binding.editTextPatologia.text.toString().trim()

        if (nombres.isEmpty()){
            binding.editTextNombres.error = "Ingrese los nombres"
            binding.editTextNombres.requestFocus()
        }else if(apellidos.isEmpty()){
            binding.editTextApellidos.error = "Ingrese los apellidos"
            binding.editTextApellidos.requestFocus()
        }else if(cedula.isEmpty()){
            binding.editTextCedula.error = "Ingrese la cedula"
            binding.editTextCedula.requestFocus()
        }else if(sexo.isEmpty()){
            binding.editTextSexo.error = "Ingrese el sexo"
            binding.editTextSexo.requestFocus()
        }else if(nacimiento.isEmpty()){
            binding.editTextNacimiento.error = "Ingrese la fecha de nacimiento"
            binding.editTextNacimiento.requestFocus()
        }else if(patologia.isEmpty()){
            binding.editTextPatologia.error = "Ingrese la patologia"
            binding.editTextPatologia.requestFocus()
        }else{
            registrarParticipante()
        }

    }

    private fun registrarParticipante() {
        progressDialog.setMessage("Creando participante")
        progressDialog.show()
        insetarInfoBD()
    }

    private fun insetarInfoBD() {
        progressDialog.setMessage("Guardando Informacion...")

        val nombreBD = nombres
        val apellidosBD = apellidos
        val cedulaBD = cedula
        val sexoBD = sexo
        val nacimientoBD = nacimiento
        val patoogiaBD = patologia

        val datosParticipante = HashMap<String, Any>()

        datosParticipante["nombres"] = "$nombreBD"
        datosParticipante["apellidos"] = "$apellidosBD"
        datosParticipante["cedula"] = "$cedulaBD"
        datosParticipante["sexo"] = "$sexoBD"
        datosParticipante["FechaNacimiento"] = "$nacimientoBD"
        datosParticipante["patologia"] = "$patoogiaBD"

        val references = FirebaseDatabase.getInstance().getReference("Paticipantes")
        references.child(cedulaBD)
            .setValue(datosParticipante)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, mis_participantes::class.java))
                finish()
            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this, "fallo el registro en BD debido a ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
