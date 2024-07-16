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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.proyecto.senes.databinding.ActivityRegistroParticipanteBinding
import com.proyecto.senes.modelo.Participante

class registro_participante : AppCompatActivity() {

    private lateinit var binding : ActivityRegistroParticipanteBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var references: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroParticipanteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor")
        progressDialog.setCanceledOnTouchOutside(false)

        references = FirebaseDatabase.getInstance().getReference("Participantes")

        binding.buttonRegistroParticipante.setOnClickListener{

            val nombres = binding.editTextNombres.text.toString().trim()
            val apellidos = binding.editTextApellidos.text.toString().trim()
            val sexo = binding.editTextSexo.text.toString().trim()
            val nacimiento = binding.editTextNacimiento.text.toString().trim()
            val patologia = binding.editTextPatologia.text.toString().trim()

            if (nombres.isEmpty()){
                binding.editTextNombres.error = "Ingrese los nombres"
                binding.editTextNombres.requestFocus()
            }else if(apellidos.isEmpty()){
                binding.editTextApellidos.error = "Ingrese los apellidos"
                binding.editTextApellidos.requestFocus()
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
                progressDialog.setMessage("Creando participante")
                progressDialog.show()
                progressDialog.setMessage("Guardando Informacion...")


                val id = references.child("Participantes").push().key

                val participantem = Participante (id, nombres, apellidos,sexo,nacimiento,patologia)

                references.child(id!!)
                    .setValue(participantem)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        startActivity(Intent(this, menu::class.java))
                        finish()
                    }
                    .addOnFailureListener{e->
                        progressDialog.dismiss()
                        Toast.makeText(this, "fallo el registro en BD debido a ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
