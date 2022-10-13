package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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
    private lateinit var minValue:String
    private lateinit var ipV4:String
    private lateinit var edtValTM: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_invest_money)
        val bundle= intent.extras
        getPreference()
        minValue = bundle?.getString("minValue").toString()
        eMailI = bundle?.getString("eMail").toString()
        eMailC = bundle?.getString("eMailc").toString()
        tMoney = bundle?.getString("tMoney").toString()
        nameI = bundle?.getString("name").toString()
        //Toast.makeText(this, "$minValue", Toast.LENGTH_LONG).show()
        //Elementos de vista
        val minVal = findViewById<TextView>(R.id.minVal)
        val twtMoney =findViewById<TextView>(R.id.twtMoney)
        val nameInvestt = findViewById<TextView>(R.id.nameInvestt)

        minVal.text = minValue
        edtValTM = findViewById(R.id.edtValTM)
        nameInvestt.text= nameI
        twtMoney.text = tMoney

    }
    fun evalutateRange(view: View){
        val nVal =edtValTM.text.toString()
        if((tMoney.toInt())>nVal!!.toInt()){
            if((nVal!!.toInt())>minValue.toInt()){
                invest(nVal)
            }else{
                Toast.makeText(this, "La inversión es menor al minímo", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "La inversión supera el monto", Toast.LENGTH_LONG).show()
        }
        edtValTM.text.toString()
    }
    private fun getPreference() {
        val pref = getSharedPreferences("config", Context.MODE_PRIVATE)
        ipV4 = pref.getString("ip","0").toString()
    }
    //Invierte en la inversión seleccionada
    private fun invest(nVal: String) {
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
                paramss.put("nMoney", nVal)
                paramss.put("eMailc", eMailC)
                paramss.put("eMaili", eMailI)
                return paramss
            }
        }
        queue.add(result)
    }
    fun getValue(){

    }
    //Devuelve a la pantalla anterior con parámetros asignados
    private fun initIntent(){
        val intent = Intent(this, clientActivity1::class.java)
        intent.putExtra("user", eMailC)
        startActivity(intent)
    }
}