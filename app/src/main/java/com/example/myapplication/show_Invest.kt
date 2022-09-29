package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.ModelDAO.ListIAnvestE
import com.example.myapplication.modelRWAdapter.ListAShowInvest

class show_Invest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_invest)
        val siAddInvest = findViewById<Button>(R.id.siAddInvest)
        var tFont=findViewById<TextView>(R.id.tFont3)
        var numClients=findViewById<TextView>(R.id.numClients3)
        var numInvest=findViewById<TextView>(R.id.numInvest3)
        //Conexión a MySQL
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.14:8081/API_REST_BD_CON/admin/fontDetail.php"
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                tFont.text=response.getString("tMoney")
                numClients.setText(response.getString("clients"))
                numInvest.setText(response.getString("investss"))
            }, {error->
                Toast.makeText(this,"$error ahhh", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest)
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