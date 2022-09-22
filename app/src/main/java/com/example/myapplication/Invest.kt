package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListClientE
import com.example.myapplication.ModelDAO.ListInvestE
import com.example.myapplication.modelRWAdapter.ListALClient
import com.example.myapplication.modelRWAdapter.ListALInvest

class Invest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest)
        val recyclerView = findViewById<RecyclerView>(R.id.ListClients)
        var clients = mutableListOf<ListClientE>()
        clients.add(ListClientE("William Fernando Roa Vargas","2500"))
        clients.add(ListClientE("Luisa Fernanda Albarracín Mendoza","2500"))
        clients.add(ListClientE("Eduard Camilo Ortega Sánchez","3500"))
        clients.add(ListClientE("Santiago Alejandro Caro Cárdenas","1500"))
        val adapter = ListALClient(clients)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}