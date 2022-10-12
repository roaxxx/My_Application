package com.example.myapplication

import android.content.Context
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

class Detail_Invest : AppCompatActivity() {
    private var clients = mutableListOf<ListClientE>()
    private lateinit var adapter:ListALClient
    private lateinit var ipV4:String
    private lateinit var idInvest:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_invest)
        getPreference()
        //Recepción de valor enviado en intent
        val bundle= intent.extras
        idInvest =bundle?.getString("user").toString()
        //Visulizadores de texto
        getInvestDetails()
        getClientList()

    }

    private fun getClientList() {
        val queue = Volley.newRequestQueue(this)
        val url2 = "http://$ipV4:8081/API_REST_BD_CON/investiment/invesShowClient.php?id=$idInvest"
        val jsRequest2 = JsonObjectRequest(
            Request.Method.GET,url2,null,
            { response ->
                try{
                    val jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        if(!jsonObject.getString("name").equals("null")){
                            clients.add(ListClientE(jsonObject.getString("name"),
                                jsonObject.getString("invested_money")))
                        }
                    }
                    initRecyclerView(clients)
                }
                catch (e: JSONException){
                    Toast.makeText(this,"$e", Toast.LENGTH_LONG).show()
                }
            }, {error->
                Toast.makeText(this,"Sin clientes", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest2)
    }

    private fun getInvestDetails() {
        val iDName=findViewById<TextView>(R.id.iDName)
        val idTinvest=findViewById<TextView>(R.id.idTinvest)
        val idminValue=findViewById<TextView>(R.id.idminValue)
        val iDnCs=findViewById<TextView>(R.id.iDnCs)
        val iDDescp=findViewById<TextView>(R.id.iDDescp)
        val queue = Volley.newRequestQueue(this)
        val url = "http://$ipV4:8081/API_REST_BD_CON/investiment/showinvest.php?id=$idInvest"
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                iDName.text=response.getString("name_investiment")
                idTinvest.text=response.getString("invested")
                idminValue.text=response.getString("min_value")
                iDnCs.text=response.getString("clients")
                iDDescp.text=response.getString("invest_Desp")
            }, {error->
                Toast.makeText(this,"$error ahhh", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest)
    }
    private fun getPreference() {
        val pref = getSharedPreferences("config", Context.MODE_PRIVATE)
        ipV4 = pref.getString("ip","0").toString()
    }
    private fun initRecyclerView(clients: MutableList<ListClientE>) {
        val recyclerView = findViewById<RecyclerView>(R.id.ListClients)
        adapter = ListALClient(clients)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}