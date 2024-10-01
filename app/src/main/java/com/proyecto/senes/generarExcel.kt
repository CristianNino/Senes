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
import com.proyecto.senes.modelo.ExcelParticipante
import com.proyecto.senes.modelo.ExcelPuntucacion
import com.proyecto.senes.modelo.ExcelResultado
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class generarExcel : AppCompatActivity() {

    private lateinit var binding: ActivityGenerarExcelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerarExcelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = FirebaseDatabase.getInstance()
        val referencia1 = database.getReference("Participantes")
        val referencia2 = database.getReference("Puntuacion")
        val referencia3 = database.getReference("Resultados")

        val listPart = mutableListOf<ExcelParticipante>()
        val listPuntua = mutableListOf<ExcelPuntucacion>()
        val listResul = mutableListOf<ExcelResultado>()

        referencia1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    val user = snap.getValue(ExcelParticipante::class.java)
                    user?.let { listPart.add(it) }
                }
                    if (listPuntua.isNotEmpty() && listResul.isNotEmpty()) {
                        crearExcel(listPart, listPuntua, listResul)
                    }
                }
            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Error al leer el valor.", error.toException())
            }
        })
        referencia2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    val puntua = snap.getValue(ExcelPuntucacion::class.java)
                    puntua?.let {
                        listPuntua.add(it)
                    }
                }
                if (listPart.isNotEmpty() && listResul.isNotEmpty()) {
                    crearExcel(listPart, listPuntua, listResul)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Error al leer el valor.", error.toException())
            }
        })
        referencia3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    val resul = snap.getValue(ExcelResultado::class.java)
                    resul?.let { listResul.add(it)
                    }
                }
                if (listPart.isNotEmpty() && listPuntua.isNotEmpty()) {
                    crearExcel(listPart, listPuntua, listResul)
                }
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

    /*private fun permisos() {
        solicitarPermisos.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE

            )
        )
    }*/
    private fun crearExcel(
        listaParti: List<ExcelParticipante>,
        listaPuntua: List<ExcelPuntucacion>,
        listaResul: List<ExcelResultado>
    ) {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val fileName = "AAAAAB.xlsx"

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
        headerRow.createCell(4).setCellValue("Codigo")


        for ((index, participante) in listaParti.withIndex()) {
            val row = sheet.createRow(index + 1)
            row.createCell(0).setCellValue(participante.nombres)
            row.createCell(1).setCellValue(participante.apellidos)
            row.createCell(2).setCellValue(participante.edadp)
            row.createCell(3).setCellValue(participante.sexo)
            row.createCell(4).setCellValue(participante.id)
        }

        val hoja: Sheet = workbook.createSheet("Hoja 2")
        val hoja2 = hoja.createRow(0)

        hoja2.createCell(0).setCellValue("Codigo")
        hoja2.createCell(1).setCellValue("Chair_Stand_Test")
        hoja2.createCell(2).setCellValue("Resultado")
        hoja2.createCell(3).setCellValue("Arm_Curl_Text")
        hoja2.createCell(4).setCellValue("Resultado")
        hoja2.createCell(5).setCellValue("Chair_Sit_and_Reach_Test")
        hoja2.createCell(6).setCellValue("Resultado")
        hoja2.createCell(7).setCellValue("Back_Scratch_Test")
        hoja2.createCell(8).setCellValue("Resultado")
        hoja2.createCell(9).setCellValue("Foot_Up_and_Go_Test")
        hoja2.createCell(10).setCellValue("Resultado")
        hoja2.createCell(11).setCellValue("minute_step_test")
        hoja2.createCell(12).setCellValue("Resultado")

        for ((index1, puntuacion) in listaPuntua.withIndex()) {
            val row = hoja.createRow(index1 + 1)
            row.createCell(0).setCellValue(puntuacion.id_participante)
            row.createCell(1).setCellValue(puntuacion.chair_Stand_Test)
            row.createCell(3).setCellValue(puntuacion.arm_Curl_Text)
            row.createCell(5).setCellValue(puntuacion.chair_Sit_and_Reach_Test)
            row.createCell(7).setCellValue(puntuacion.back_Scratch_Test)
            row.createCell(9).setCellValue(puntuacion.foot_Up_and_Go_Test)
            row.createCell(11).setCellValue(puntuacion.minute_step_test)
        }

        for ((index1, resultado) in listaResul.withIndex()) {
            val row = hoja.getRow(index1 + 1) ?: hoja.createRow(index1 + 1)
            row.createCell(2).setCellValue(resultado.resultado_Chair_Stand_Test)
            row.createCell(4).setCellValue(resultado.resultado_Arm_Curl_Text)
            row.createCell(6).setCellValue(resultado.resultado_Chair_Sit_and_Reach_Test)
            row.createCell(8).setCellValue(resultado.resultado_Back_Scratch_Test)
            row.createCell(10).setCellValue(resultado.resultado_Foot_Up_and_Go_Test)
            row.createCell(12).setCellValue(resultado.resultado_minute_step_test)
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


