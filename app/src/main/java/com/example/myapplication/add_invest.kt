package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class add_invest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_invest)
        val btnAddInvest = findViewById<Button>(R.id.btnAddInvest)
        btnAddInvest.setOnClickListener {
            createInvest()
        }
    }
    //Obtiene paràmetros de vista y crea inversiones
    private fun createInvest() {
        val e_mailI=findViewById<EditText>(R.id.e_mailI)
        val etNInvest=findViewById<EditText>(R.id.etNInvest)
        val etPasswI=findViewById<EditText>(R.id.etPasswI)
        val etminVal=findViewById<EditText>(R.id.etminVal)
        val etDecsp=findViewById<EditText>(R.id.etDecsp)
        val queue = Volley.newRequestQueue(this)
        val url ="http://192.168.10.16:8081/API_REST_BD_CON/admin/investiments/createinvestiment.php"
        val result = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener<String> { response ->
                val intent: Intent?
                intent= Intent(this, FondMain::class.java)
                startActivity(intent)
            }, Response.ErrorListener{ error ->
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val paramss = HashMap<String,String>()
                paramss.put("name",etNInvest?.text.toString())
                paramss.put("eMail",e_mailI?.text.toString())
                paramss.put("passw",etPasswI?.text.toString())
                paramss.put("minValue",etminVal?.text.toString())
                paramss.put("descp",etDecsp?.text.toString())
                return paramss
            }
        }
        queue.add(result)
    }
}