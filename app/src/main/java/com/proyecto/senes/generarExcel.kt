package com.proyecto.senes

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.proyecto.senes.databinding.ActivityGenerarExcelBinding
import com.proyecto.senes.modelo.Excel
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class generarExcel : AppCompatActivity() {

    private lateinit var binding: ActivityGenerarExcelBinding

    private lateinit var solicitarPermisos: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerarExcelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Participantes")

        /*solicitarPermisos = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            val aceptados = it.all { it.value }
            if(aceptados){
                Toast.makeText(this, "permisos concedidos", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "No se aceptaron los permisos", Toast.LENGTH_SHORT).show()
            }
        }



        permisos()*/

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Excel>()
                for(snap in snapshot.children){
                    val user = snap.getValue(Excel::class.java)
                    if (user != null) {
                        Log.d("FirebaseData", "Nombre: ${user.nombres}, Apellido: ${user.apellidos}, Edad: ${user.edades}, Genero: ${user.generos}")
                        list.add(user)
                    }
                }
                crearExcel(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Error al leer el valor.", error.toException())
            }
        })

        val btnvolver = findViewById<ImageButton>(R.id.imageButtonExcelVolver)


        btnvolver.setOnClickListener {
            val intent = Intent(this, menu::class.java)
            startActivity(intent)
        }

    }

    private fun permisos() {
        solicitarPermisos.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE

            )
        )
    }
    private fun crearExcel(listaRegistros: List<Excel>){
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val fileName = "AAd.xlsx"

        // Crear un nuevo libro de trabajo Excel en formato .xlsx
        val workbook = XSSFWorkbook()

        // Crear una hoja de trabajo (worksheet)
        val sheet: Sheet = workbook.createSheet("Hoja 1")

        // Crear una fila en la hoja
        val headerRow = sheet.createRow(0)

        headerRow.createCell(0).setCellValue("Nombres")
        headerRow.createCell(1).setCellValue("Apellidos")
        headerRow.createCell(2).setCellValue("Edades")
        headerRow.createCell(3).setCellValue("Generos")



        for ((index, data) in listaRegistros.withIndex()) {
            val row = sheet.createRow(index + 1)
            row.createCell(0).setCellValue(data.nombres)
            row.createCell(1).setCellValue(data.apellidos)
            row.createCell(2).setCellValue(data.edades)
            row.createCell(3).setCellValue(data.generos)
        }

        // Guardar el libro de trabajo (workbook) en almacenamiento externo
        try {
            val fileOutputStream = FileOutputStream(
                File(path, fileName)
            )
            workbook.write(fileOutputStream)
            fileOutputStream.close()
            workbook.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}

