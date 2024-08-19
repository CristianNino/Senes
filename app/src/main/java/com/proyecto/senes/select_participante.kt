package com.proyecto.senes

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.proyecto.senes.Adapter.AdapterSelect
import com.proyecto.senes.modelo.Participante

class select_participante : AppCompatActivity() {

    private lateinit var selectlista: ArrayList<Participante>
    private lateinit var selectRecycle: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var Adapterselect: AdapterSelect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_select_participante)

        selectRecycle =findViewById(R.id.rvselectparticipante)
        selectRecycle.layoutManager = LinearLayoutManager(this)

        val atras = findViewById<ImageButton>(R.id.imageButtonselectatras)

        selectlista = arrayListOf<Participante>()

        obtenerSelect()

        atras.setOnClickListener {
            val intent = Intent(this, mis_baterias::class.java)
            startActivity(intent)
        }
    }
    private fun obtenerSelect() {
        database = FirebaseDatabase.getInstance().getReference("Participantes")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(selectSnapshot in snapshot.children){
                        val selectpart = selectSnapshot.getValue(Participante::class.java)
                        selectlista.add(selectpart!!)
                    }
                    Adapterselect = AdapterSelect(selectlista)
                    selectRecycle.adapter = Adapterselect
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}