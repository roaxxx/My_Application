package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class add_investMoney : AppCompatActivity() {
    private lateinit var eMailI:String
    private lateinit var eMailC:String
    private lateinit var tMoney:String
    private lateinit var nameI:String
    private lateinit var ipV4:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_invest_money)
        val bundle= intent.extras
        getPreference()
        eMailI = bundle?.getString("eMail").toString()
        eMailC = bundle?.getString("eMailc").toString()
        tMoney = bundle?.getString("tMoney").toString()
        nameI = bundle?.getString("name").toString()
        val twtMoney =findViewById<TextView>(R.id.twtMoney)
        val nameInvestt = findViewById<TextView>(R.id.nameInvestt)
        val btnAddMI = findViewById<Button>(R.id.btnAddMI)
        nameInvestt.text= nameI
        twtMoney.text = tMoney
        btnAddMI.setOnClickListener {invest()}
    }
    private fun getPreference() {
        val pref = getSharedPreferences("config", Context.MODE_PRIVATE)
        ipV4 = pref.getString("ip","0").toString()
    }
    //Invierte en la inversión seleccionada
    private fun invest() {
        Toast.makeText(this, " C $eMailC  I $eMailI", Toast.LENGTH_LONG).show()
        val queue = Volley.newRequestQueue(this)
        val url = "http://$ipV4:8081/API_REST_BD_CON/client/invest.php"
        val result = object : StringRequest(
            Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                initIntent()
            }, Response.ErrorListener { error ->
                Toast.makeText(this, "Error en la conexión", Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): MutableMap<String, String>? {
                val paramss = HashMap<String, String>()
                paramss.put("nMoney", "0")
                paramss.put("eMailc", eMailC)
                paramss.put("eMaili", eMailI)
                return paramss
            }
        }
        queue.add(result)
    }
    //Devuelve a la pantalla anterior con parámetros asignados
    private fun initIntent(){
        val intent = Intent(this, clientActivity1::class.java)
        intent.putExtra("user", eMailC)
        startActivity(intent)
    }
}