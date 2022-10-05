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
import com.example.myapplication.ModelDAO.ListInvestE
import com.example.myapplication.modelRWAdapter.ListALInvest
import org.json.JSONException

class clientActivity1 : AppCompatActivity() {
    private var invest = mutableListOf<ListInvestE>()
    private lateinit var adapter:ListALInvest
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client1)
        val showH = findViewById<Button>(R.id.view_h)
        val bundle= intent.extras
        val idCard =bundle?.getString("user")
        var nameC= findViewById<TextView>(R.id.name_client)
        var tCap= findViewById<TextView>(R.id.cap_fond)
        var invested= findViewById<TextView>(R.id.invertido)
        var age= findViewById<TextView>(R.id.age_view)


        //Conexión a la API
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.16:8081/API_REST_BD_CON/client/showclient.php?name=$idCard"
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                nameC.text=response.getString("name")
                tCap.setText(response.getString("tMoney"))
                invested.setText(response.getString("invested_money"))
                age.text=response.getString("age")
            }, {error->
                Toast.makeText(this,"$error",Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest)
        showH.setOnClickListener{
            startActivity(Intent(this,showClientMovs::class.java))
        }

        val url2 = "http://192.168.10.16:8081/API_REST_BD_CON/client/cinvest.php?idCard=$idCard"
        val jsRequest2 = JsonObjectRequest(
            Request.Method.GET,url2,null,
            { response ->
                try{
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
                        invest.add(
                            ListInvestE(jsonObject.getString("id_investiment"),
                                jsonObject.getString("name_investiment"),
                                jsonObject.getString("invested")
                            )
                        )
                    }
                    initRecyclerView(invest)
                }
                catch (e: JSONException){
                    Toast.makeText(this,"aa$e", Toast.LENGTH_LONG).show()
                }
            }, {error->
                Toast.makeText(this,"i$error", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest2)
    }
    //Inicializa el recyclerView con mutableList
    fun initRecyclerView(invest: MutableList<ListInvestE>) {
        val recyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
        adapter = ListALInvest(
            this.invest,
            onClickListener = { i -> addMoney(i)},
            onClickDelete = { i -> withdrawMoney(i)}
            )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    //Devuelve una posición especifica en la cual se encuentra una inversión:Para invertir
    fun addMoney(i:Int){
        Toast.makeText(this,"$i",Toast.LENGTH_SHORT).show()
    }
    //Devuelve una posición especifica en la cual se encuentra una inversión:Para retirar
    fun withdrawMoney(i:Int){
        Toast.makeText(this,"$i",Toast.LENGTH_SHORT).show()
    }
}