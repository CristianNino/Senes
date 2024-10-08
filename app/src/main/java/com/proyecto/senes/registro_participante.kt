package com.proyecto.senes

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.DatePicker
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

        seleccionGenero()
        calendariofecha()

        binding.buttonRegistroParticipante.setOnClickListener{

            val nombres = binding.editTextNombres.text.toString().trim()
            val apellidos = binding.editTextApellidos.text.toString().trim()
            val sexo = binding.autoCompleteTextsexo.text.toString().trim()
            val nacimiento = binding.textViewfecha.text.toString().trim()
            val patologia = binding.editTextPatologia.text.toString().trim()
            val edad = binding.textViewEdad.text.toString().trim()

            if (nombres.isEmpty()){
                binding.editTextNombres.error = "Ingrese los nombres"
                binding.editTextNombres.requestFocus()
            }else if(apellidos.isEmpty()){
                binding.editTextApellidos.error = "Ingrese los apellidos"
                binding.editTextApellidos.requestFocus()
            }else if(nacimiento.isEmpty()){
                binding.textViewfecha.error = "Ingrese los apellidos"
                binding.textViewfecha.requestFocus()
            }else if(sexo.isEmpty()){
                binding.autoCompleteTextsexo.error = "Ingrese el sexo"
                binding.autoCompleteTextsexo.requestFocus()
            }else if(patologia.isEmpty()){
                binding.editTextPatologia.error = "Ingrese la patologia"
                binding.editTextPatologia.requestFocus()
            }else{
                progressDialog.setMessage("Creando participante")
                progressDialog.show()
                progressDialog.setMessage("Guardando Informacion...")


                val id = references.child("Participantes").push().key
                val participantem = Participante (id, nombres, apellidos,sexo,nacimiento,patologia,edad)

                references.child(id!!)
                    .setValue(participantem, edad)
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

    private fun seleccionGenero() {
        val gen = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextsexo)

        val sugerencias = resources.getStringArray(R.array.optiones)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, sugerencias)

        gen.setAdapter(adapter)

        gen.setOnItemClickListener{ _,_, posicion,_ ->
            val elemento = sugerencias[posicion]
        }
        gen.setOnClickListener{
            gen.showDropDown()
        }
    }

    private fun calendariofecha() {
        val calendario = Calendar.getInstance()

        val year = calendario.get(Calendar.YEAR)
        val month = calendario.get(Calendar.MONTH)
        val day = calendario.get(Calendar.DAY_OF_MONTH)

        binding.buttoncalendario.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                    val edad = calcularEdad(selectedYear, selectedMonth, selectedDay)
                    val edadselesccionada = "${selectedDay} / ${selectedMonth + 1} / ${selectedYear}"
                    binding.textViewfecha.text = " $edadselesccionada "
                    binding.textViewEdad.text = "$edad"
                }, year, month, day)
            datePickerDialog.show()
        }
    }
    private fun calcularEdad(year: Int, month: Int, day: Int): Int {
        val saberedad = Calendar.getInstance()
        saberedad.set(year, month, day)
        val today = Calendar.getInstance()
        var edadP = today.get(Calendar.YEAR) - saberedad.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < saberedad.get(Calendar.DAY_OF_YEAR)) {
            edadP--
        }
        return edadP
    }
}

