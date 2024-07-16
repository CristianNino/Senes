package com.proyecto.senes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.Snapshot
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.proyecto.senes.Adapter.AdapterParticipante
import com.proyecto.senes.databinding.ActivityMisParticipantesBinding
import com.proyecto.senes.modelo.Participante

class mis_participantes : AppCompatActivity() {

    private lateinit var binding: ActivityMisParticipantesBinding
    private lateinit var participantelist : ArrayList<Participante>
    private lateinit var participanteRecyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var adapterParticipante : AdapterParticipante

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMisParticipantesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //participanteRecyclerView = binding.rvParticipantes
        participanteRecyclerView = findViewById<RecyclerView>(R.id.rvParticipantes)
        participanteRecyclerView.layoutManager = LinearLayoutManager(this)
        participanteRecyclerView.setHasFixedSize(true)

        participantelist = arrayListOf<Participante>()

        database = FirebaseDatabase.getInstance().getReference("Participantes")

        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (participanteSnapshot in p0.children){
                        val participante = participanteSnapshot.getValue(Participante::class.java)
                        participantelist.add(participante!!)
                    }
                    adapterParticipante = AdapterParticipante()
                    participanteRecyclerView.adapter = adapterParticipante
                }

            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}
/*override fun onDataChange(p0: DataSnapshot) {
    if (p0.exists()){
        for (participanteSnapshot in p0.children){
            val participante = participanteSnapshot.getValue(Participante::class.java)
            participantelist.add(participante!!)
        }
        adapterParticipante = AdapterParticipante()
        participanteRecyclerView.adapter = adapterParticipante
    }

}
override fun onCancelled(p0: DatabaseError) {
    TODO("Not yet implemented")
}

}*/