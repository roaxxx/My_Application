package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class FondWithDr : AppCompatActivity() {
    private lateinit var ipV4:String
    private lateinit var tMoney:String
    private lateinit var eMail:String
    private lateinit var edtValTM:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fond_with_dr)
        val bundle= intent.extras
        getPreference()
        tMoney =bundle?.getString("tMoney").toString()
        eMail =bundle?.getString("eMail").toString()
        //Se instancian los widgets de la vista
        val twtMoney = findViewById<TextView>(R.id.twtMoney)
        edtValTM = findViewById(R.id.edtValTM)
        twtMoney.text= tMoney
    }

    private fun getPreference() {
        val pref = getSharedPreferences("config", Context.MODE_PRIVATE)
        ipV4 = pref.getString("ip","0").toString()
    }

    fun withdraw(view: View){
        val edtValTM = findViewById<EditText>(R.id.edtValTM)
        val valTm= edtValTM.text.toString()
        if(valTm.toInt()<tMoney.toInt()){
            fundSetWithdraw("${tMoney.toInt()-valTm.toInt()}")
        }else{
            Toast.makeText(this, "El valor supera el disponible", Toast.LENGTH_LONG).show()
        }
    }

    private fun fundSetWithdraw(valTm: String) {
        val queue = Volley.newRequestQueue(this)
        val url ="http://$ipV4:8081/API_REST_BD_CON/client/wdfromfont.php"
        val result = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "Se a completado la transacción", Toast.LENGTH_LONG).show()
                intent= Intent(this, clientActivity1::class.java)
                intent.putExtra("user", eMail)
                startActivity(intent)
            },Response.ErrorListener{ error ->
                Toast.makeText(this, "Sin conexión", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val paramss = HashMap<String,String>()
                paramss.put("money",valTm)
                paramss.put("eMail",eMail)
                return paramss
            }
        }
        queue.add(result)
    }

}