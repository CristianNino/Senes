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
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.proyecto.senes.Adapter.AdapterParticipante
import com.proyecto.senes.modelo.Participante

class miparticipante : AppCompatActivity() {


    private lateinit var participanteList: ArrayList<Participante>
    private lateinit var participanteRecycle: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var adapterParticipante: AdapterParticipante


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_miparticipante)

        participanteRecycle = findViewById(R.id.rvParticipante)
        participanteRecycle.layoutManager = LinearLayoutManager(this)

        participanteList = arrayListOf<Participante>()

        getParticipante()

        val btnatras = findViewById<ImageButton>(R.id.imageButtonatras)

        btnatras.setOnClickListener {
            navigateToatras()
        }
    }

    private fun getParticipante() {
        database = FirebaseDatabase.getInstance().getReference("Participantes")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (participanteSnapshot in snapshot.children){
                        val participante = participanteSnapshot.getValue(Participante::class.java)
                        participanteList.add(participante!!)
                    }
                    adapterParticipante = AdapterParticipante(participanteList)
                    participanteRecycle.adapter = adapterParticipante
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun navigateToatras() {
        val intent = Intent(this, menu::class.java)
        startActivity(intent)
    }
}
