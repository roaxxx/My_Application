package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.ModelDAO.ListClientE
import com.example.myapplication.modelRWAdapter.ListALClient
import org.json.JSONException

class Invest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest)
        val recyclerView = findViewById<RecyclerView>(R.id.ListClients)
        val bundle= intent.extras
        val idInvest =bundle?.getString("user")
        var nameI=findViewById<TextView>(R.id.nameI)
        var tInvest=findViewById<TextView>(R.id.tInvest)
        var minValue=findViewById<TextView>(R.id.minValue)
        var nClients=findViewById<TextView>(R.id.nClients)
        var iDescrip=findViewById<TextView>(R.id.iDescrip)
        //Conexión a la API
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.14:8081/API_REST_BD_CON/investiment/showinvest.php?id=$idInvest"
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                Toast.makeText(this,"Este", Toast.LENGTH_LONG).show()
                nameI.text=response.getString("name_investiment")
                tInvest.setText(response.getString("invested"))
                minValue.setText(response.getString("min_value"))
                nClients.setText(response.getString("clients"))
                iDescrip.setText(response.getString("invest_Desp"))
            }, {error->
                Toast.makeText(this,"$error ahhh", Toast.LENGTH_LONG).show()
            })
        var clients = mutableListOf<ListClientE>()
        val url2 = "http://192.168.10.14:8081/API_REST_BD_CON/investiment/invesShowClient.php?id=$idInvest"
        val jsRequest2 = JsonObjectRequest(
            Request.Method.GET,url2,null,
            { response ->
                try{
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
                        clients.add(ListClientE(jsonObject.getString("name"),jsonObject.getString("invested_money")))
                        val adapter = ListALClient(clients)

                        recyclerView.layoutManager = LinearLayoutManager(this)
                        recyclerView.adapter = adapter
                    }
                }
                catch (e: JSONException){
                    Toast.makeText(this,"$e", Toast.LENGTH_LONG).show()
                }
            }, {error->
                Toast.makeText(this,"$error ahhh", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest2)
    }
}