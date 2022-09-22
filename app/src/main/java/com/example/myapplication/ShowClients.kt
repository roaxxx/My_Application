package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListCAE
import com.example.myapplication.ModelDAO.ListInvestE
import com.example.myapplication.modelRWAdapter.ListAAShowClient
import com.example.myapplication.modelRWAdapter.ListALInvest

class ShowClients : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_clients)
        val scAddClients = findViewById<Button>(R.id.siAddInvest)
        scAddClients.setOnClickListener{
            startActivity(Intent(this,add_Client::class.java))
        }
        val recyclerView = findViewById<RecyclerView>(R.id.ListShowC)
        var sClients = mutableListOf<ListCAE>()
        sClients.add(ListCAE("William Fernando Roa Vargas","2500"))
        sClients.add(ListCAE("Luisa Fernanda Albarracín Mendoza","2500"))
        sClients.add(ListCAE("Eduard Camilo Ortega Sánchez","3500"))
        sClients.add(ListCAE("Santiago Alejandro Caro Cárdenas","1500"))
        val adapter = ListAAShowClient(sClients)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}