package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListInvestE
import com.example.myapplication.modelRWAdapter.ListALInvest
class clientActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client1)
        val showH = findViewById<Button>(R.id.view_h)
        showH.setOnClickListener{
            startActivity(Intent(this,showClientMovs::class.java))
        }
        val recyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
        var invest = mutableListOf<ListInvestE>()
        invest.add(ListInvestE("El dorado","2500"))
        invest.add(ListInvestE("Torres de sión","2500"))
        invest.add(ListInvestE("Los farallones","3500"))
        invest.add(ListInvestE("SunDevps","1500"))
        val adapter = ListALInvest(invest)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}