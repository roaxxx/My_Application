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
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.ModelDAO.ListInvestE
import com.example.myapplication.modelRWAdapter.ListALInvest
class clientActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client1)
        val showH = findViewById<Button>(R.id.view_h)
        val bundle= intent.extras
        val idCard =bundle?.getString("user")
        var nameC=findViewById<TextView>(R.id.name_client)
        var tCap=findViewById<TextView>(R.id.cap_fond)
        var invested=findViewById<TextView>(R.id.invertido)
        var age=findViewById<TextView>(R.id.age_view)

        nameC?.text="Si se pudo pa"
        //Conexión a la API
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.14:8081/API_REST_BD_CON/showClient.php?name=$idCard"
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                nameC.text=response.getString("name")
                tCap.setText(response.getString("tMoney"))
                invested.setText(response.getString("invested_money"))
                age.text=response.getString("age")
            }, {error->
                Toast.makeText(this,"$error ahhh",Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest)
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
    fun getClientInfo(){

    }
}