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
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.ModelDAO.ListClientE
import com.example.myapplication.ModelDAO.ListIAnvestE
import com.example.myapplication.modelRWAdapter.ListAShowInvest
import org.json.JSONException

class show_Invest : AppCompatActivity() {
    private var sInvest = mutableListOf<ListIAnvestE>()
    private lateinit var adapter:ListAShowInvest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_invest)
        val siAddInvest = findViewById<Button>(R.id.siAddInvest)
        siAddInvest.setOnClickListener{
            startActivity(Intent(this,add_invest::class.java))
        }
        getFontDetails()
        getInvestiments()
    }
    //Consulta inversiones totales
    private fun getInvestiments() {
        val queue = Volley.newRequestQueue(this)
        val url2 = "http://192.168.10.17:8081/API_REST_BD_CON/admin/investiments/ashowinvest.php"
        val jsRequest2 = JsonObjectRequest(
            Request.Method.GET,url2,null,
            { response ->
                try{
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
                        sInvest.add(ListIAnvestE(jsonObject.getString("name_investiment"),
                            jsonObject.getString("invested"),
                            jsonObject.getString("e_mail")))
                    }
                    initRecyclerView(sInvest)
                }
                catch (e: JSONException){
                    Toast.makeText(this,"$e", Toast.LENGTH_LONG).show()
                }
            }, {error->
                Toast.makeText(this,"$error", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest2)
    }
    //Consulta detalles del fondo
    private fun getFontDetails() {
        var tFont=findViewById<TextView>(R.id.tFont3)
        var numClients=findViewById<TextView>(R.id.numClients3)
        var numInvest=findViewById<TextView>(R.id.numInvest3)
        val queue = Volley.newRequestQueue(this)
        val url = "http:///192.168.10.17:8081/API_REST_BD_CON/admin/fontDetail.php"
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
    }
    //Iniciar RecyclerView
    private fun initRecyclerView(sInvest: MutableList<ListIAnvestE>) {
        val recyclerView = findViewById<RecyclerView>(R.id.ListAShowInvest)
        adapter = ListAShowInvest(sInvest,
        onClickListener = {i -> showInvestDetail(i)},
        onClickDelete = {i -> deleteInvestiment(i)})

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    //Mostrar detalles
    private fun showInvestDetail(i:Int){
        var intent = Intent(this, Detail_Invest::class.java)
        intent.putExtra("user", sInvest[i].email)
        startActivity(intent)
    }
    //Obtiene la posición de la inversión a borrar
    private fun deleteInvestiment(i:Int){
        var e_mail = sInvest[i].email
        deleteInvest(i,e_mail)
        sInvest.removeAt(i)
        adapter.notifyItemRemoved(i)
    }
    //Borrar inversión
    private fun deleteInvest(position: Int, email: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.17:8081/API_REST_BD_CON/admin/investiments/deleteivestiment.php"
        val result = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "$response", Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener{ error ->
                Toast.makeText(this, "Sin conexión", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val paramss = HashMap<String,String>()
                paramss.put("name",email)
                return paramss
            }
        }
        queue.add(result)
    }
}