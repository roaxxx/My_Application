package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.ModelDAO.Investimentss
import com.example.myapplication.ModelDAO.ListInvestE
import com.example.myapplication.modelRWAdapter.ListALInvest
import com.example.myapplication.modelRWAdapter.inAdapter
import org.json.JSONException

class Detail_Client : AppCompatActivity() {
    private var investss = mutableListOf<Investimentss>()
    private lateinit var adapter: inAdapter
    private lateinit var idCard: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_client)
        val bundle= intent.extras
        idCard = bundle?.getString("user").toString()
        showClientDetail()
        getClientInvestiments()
    }
    //Obtiene y asigna detalles del cliente seleccionado
    private fun showClientDetail() {
        var nameC= findViewById<TextView>(R.id.name_client)
        var tCap= findViewById<TextView>(R.id.cap_fond)
        var invested= findViewById<TextView>(R.id.invertido)
        var age= findViewById<TextView>(R.id.age_view)
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.17:8081/API_REST_BD_CON/client/showclient.php?name=$idCard"
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                nameC.text=response.getString("name")
                tCap.setText(response.getString("tMoney"))
                invested.setText(response.getString("invested_money"))
                age.text=response.getString("age")
            }, {error->
                Toast.makeText(this,"$error", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest)
    }
    private fun getClientInvestiments() {
        val queue = Volley.newRequestQueue(this)
        val url2 = "http://192.168.10.17:8081/API_REST_BD_CON/client/cinvest.php?idCard=$idCard"
        val jsRequest2 = JsonObjectRequest(
            Request.Method.GET,url2,null,
            { response ->
                try{
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
                        if(!jsonObject.getString("id_investiment").equals("null")){
                            investss.add(
                                Investimentss(jsonObject.getString("id_investiment"),
                                    jsonObject.getString("name_investiment"),
                                    jsonObject.getString("invested")
                                )
                            )
                        }
                    }
                    initRecyclerView(investss)
                }
                catch (e: JSONException){
                    Toast.makeText(this,"aa$e", Toast.LENGTH_LONG).show()
                }
            }, {error->
                Toast.makeText(this,"El cliente no ha invertido", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest2)
    }

    //Iniciar recyclerView
    private fun initRecyclerView(investss: MutableList<Investimentss>) {
        val recyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
        adapter = inAdapter(
            this.investss
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}