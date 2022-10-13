package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
    //Variables globales
    private var invest = mutableListOf<ListInvestE>()
    private lateinit var adapter:ListALInvest
    private lateinit var ipV4:String
    private lateinit var  idCard: String
    private var tMoney = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client1)
        getPreference()
        val btnWFont = findViewById<Button>(R.id.btnWFont)
        val bundle= intent.extras
        idCard =bundle?.getString("user").toString()
        btnWFont.setOnClickListener { withdraFromFund()  }
        //LLamados a métodos de petición
        getClientDetail()
        getClientInvestiments()
    }
    fun showClientMovs(view: View){
        startActivity(Intent(this,showClientMovs::class.java))
    }
    fun addMoney(view: View){
        val intent = Intent(this, add_FondMoney::class.java)
        intent.putExtra("tMoney", tMoney)
        intent.putExtra("eMail", idCard)
        startActivity(intent)
    }
    fun withdraFromFund(){
        val intent = Intent(this, FondWithDr::class.java)
        intent.putExtra("tMoney", tMoney)
        intent.putExtra("eMail", idCard)
        startActivity(intent)
    }

    private fun getPreference() {
        val pref = getSharedPreferences("config",Context.MODE_PRIVATE)
        ipV4 = pref.getString("ip","0").toString()
    }

    private fun getClientInvestiments() {
        val queue = Volley.newRequestQueue(this)
        val url2 = "http://$ipV4:8081/API_REST_BD_CON/client/cinvest.php?idCard=$idCard"
        val jsRequest2 = JsonObjectRequest(
            Request.Method.GET,url2,null,
            { response ->
                try{
                    val jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
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
                Toast.makeText(this,"No ha invertido", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest2)
    }

    private fun getClientDetail() {
        val nameC= findViewById<TextView>(R.id.name_client)
        val tCap= findViewById<TextView>(R.id.cap_fond)
        val invested= findViewById<TextView>(R.id.invertido)
        val age= findViewById<TextView>(R.id.age_view)
        val queue = Volley.newRequestQueue(this)
        val url = "http://$ipV4:8081/API_REST_BD_CON/client/showclient.php?name=$idCard"
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                nameC.text=response.getString("name")
                tCap.text=response.getString("tMoney")
                tMoney = response.getString("tMoney")
                invested.text=response.getString("invested_money")
                age.text=response.getString("age")
            }, {error->
                Toast.makeText(this,"$error",Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest)
    }

    //Inicializa el recyclerView con mutableList
    private fun initRecyclerView(invest: MutableList<ListInvestE>) {
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
    private fun addMoney(i:Int){
        Toast.makeText(this,"$i",Toast.LENGTH_SHORT).show()
    }
    //Devuelve una posición especifica en la cual se encuentra una inversión:Para retirar
    private fun withdrawMoney(i:Int){
        Toast.makeText(this,"$i",Toast.LENGTH_SHORT).show()
    }
}