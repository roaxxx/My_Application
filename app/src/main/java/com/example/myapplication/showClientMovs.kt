package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.MovsListDao
import com.example.myapplication.modelRWAdapter.MovsListAdapter

class showClientMovs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_client_movs)
        val recyclerView = findViewById<RecyclerView>(R.id.movsClientss)
        var hMovs = mutableListOf<MovsListDao>()
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $2500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $5500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $2500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $3500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $3500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $3500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $3500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $3500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $3500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $3500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $3500"))
        hMovs.add(MovsListDao("Has retirado de la inversión el dorado un total de $3500"))

        val adapter = MovsListAdapter(hMovs)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}