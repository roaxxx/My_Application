package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListIAnvestE
import com.example.myapplication.ModelDAO.ListInvestE
import com.example.myapplication.modelRWAdapter.ListALInvest
import com.example.myapplication.modelRWAdapter.ListAShowInvest

class show_Invest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_invest)
        val siAddInvest = findViewById<Button>(R.id.siAddInvest)
        siAddInvest.setOnClickListener{
            startActivity(Intent(this,add_invest::class.java))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.ListAShowInvest)
        var sInvest = mutableListOf<ListIAnvestE>()
        sInvest.add(ListIAnvestE("El dorado","2500"))
        sInvest.add(ListIAnvestE("Torres de sión","2500"))
        sInvest.add(ListIAnvestE("Los farallones","3500"))
        sInvest.add(ListIAnvestE("SunDevps","1500"))
        sInvest.add(ListIAnvestE("El brudder","11500"))
        val adapter = ListAShowInvest(sInvest)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}