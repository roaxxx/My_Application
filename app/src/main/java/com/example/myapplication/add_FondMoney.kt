package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.ModelDAO.ListIAnvestE
import com.example.myapplication.modelRWAdapter.ListToInvest
import org.json.JSONException

class add_FondMoney : AppCompatActivity() {
    private lateinit var adapter:ListToInvest
    private var sInvest = mutableListOf<ListIAnvestE>()
    private var tMoney = "0"
    private lateinit var eMail:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_fond_money)
        val bundle= intent.extras
        eMail = bundle?.getString("eMail").toString()
        tMoney  = bundle?.getString("tMoney").toString()
        val twValF = findViewById<TextView>(R.id.twtMoney)
        val btnUpVal = findViewById<Button>(R.id.btnAddMI)
        val edtValTM = findViewById<EditText>(R.id.edtValTM)
        twValF.text = tMoney
        getInvestiments()
        btnUpVal.setOnClickListener {
            val nMoney = edtValTM.text.toString()
            addFontMoney(tMoney.toInt()+nMoney.toInt())
        }
    }
    //Consulta las inversiones disponibles
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
    //Iniciar recyclerView
    private fun initRecyclerView(sInvest: MutableList<ListIAnvestE>) {
        val recyclerView = findViewById<RecyclerView>(R.id.listInvest)
        adapter = ListToInvest(sInvest,
            onClickListener = {i -> investInto(i)})
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun investInto(i: Int) {
        var intent: Intent?
        intent = Intent(this, add_investMoney::class.java)
        intent!!.putExtra("tMoney", tMoney)
        intent!!.putExtra("eMail", sInvest[i].email)
        intent!!.putExtra("eMailc", eMail)
        intent!!.putExtra("name", sInvest[i].nameI)
        startActivity(intent)
    }


    //Consignar dinero a la cuenta cliente
    private fun addFontMoney(i: Int) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.17:8081/API_REST_BD_CON/client/addFontMoney.php"
        val result = object : StringRequest(
            Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                refreshActity()
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Error en la conexión", Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): MutableMap<String, String>? {
                val paramss = HashMap<String, String>()
                paramss.put("nMoney", "$i")
                paramss.put("eMail", eMail)
                return paramss
            }
        }
        queue.add(result)
    }

    private fun refreshActity() {

    }
}