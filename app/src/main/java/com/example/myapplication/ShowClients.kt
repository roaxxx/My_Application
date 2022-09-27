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
import com.example.myapplication.ModelDAO.ListCAE
import com.example.myapplication.modelRWAdapter.ListAAShowClient

class ShowClients : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_clients)
        val scAddClients = findViewById<Button>(R.id.siAddInvest)
        var tFont=findViewById<TextView>(R.id.tFont3)
        var numClients=findViewById<TextView>(R.id.numClients3)
        var numInvest=findViewById<TextView>(R.id.numInvest3)
        //Conexión a MySQL
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.14:8081/API_REST_BD_CON/fontDetail.php"
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