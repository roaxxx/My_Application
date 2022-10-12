package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
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
    private var clients = mutableListOf<ListClientE>()
    private lateinit var adapter:ListALClient
    private lateinit var idInvest:String
    private lateinit var nameIv:String
    private lateinit var valInv:String
    private lateinit var ipV4:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest)
        getPreference()
        val bundle= intent.extras
        idInvest = bundle?.getString("user").toString()
        val editIName=findViewById<ImageButton>(R.id.editIName)
        val editMinVal=findViewById<ImageButton>(R.id.editMinVal)
        //Conexión a la API
        getInvestimentDetail()
        getCLients()
        var intent:Intent?
        editIName.setOnClickListener {
            intent = Intent(this, EditIvestName::class.java)
            intent!!.putExtra("nameI", nameIv)
            intent!!.putExtra("eMail", idInvest)
            startActivity(intent)
        }
        editMinVal.setOnClickListener {
            intent = Intent(this, EditInvestMinVal::class.java)
            intent!!.putExtra("valInv", valInv)
            intent!!.putExtra("eMail", idInvest)
            startActivity(intent)
        }
    }
    private fun getPreference() {
        val pref = getSharedPreferences("config", Context.MODE_PRIVATE)
        ipV4 = pref.getString("ip","0").toString()
    }
    //Consulta el listado de clientes de la inversión
    private fun getCLients() {
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
                Toast.makeText(this,"$error ahhh", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest2)
    }
    //Consulta detalles de la inversión
    private fun getInvestimentDetail() {
        val nameI=findViewById<TextView>(R.id.nameI)
        val tInvest=findViewById<TextView>(R.id.tInvest)
        val minValue=findViewById<TextView>(R.id.minValue)
        val nClients=findViewById<TextView>(R.id.nClients)
        val iDescrip=findViewById<TextView>(R.id.iDescrip)
        val queue = Volley.newRequestQueue(this)
        val url = "http://$ipV4:8081/API_REST_BD_CON/investiment/showinvest.php?id=$idInvest"
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                nameIv = response.getString("name_investiment")
                valInv = response.getString("min_value")
                nameI.text=response.getString("name_investiment")
                tInvest.text=response.getString("invested")
                minValue.text=response.getString("min_value")
                nClients.text=response.getString("clients")
                iDescrip.text=response.getString("invest_Desp")
            }, {error->
                Toast.makeText(this,"$error ahhh", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest)
    }
    //Inciar RecyclerView
    private fun initRecyclerView(clients: MutableList<ListClientE>) {
        val recyclerView = findViewById<RecyclerView>(R.id.ListClients)
        adapter = ListALClient(clients)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}