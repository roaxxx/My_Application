package com.example.myapplication

import android.content.Context
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

class add_Client : AppCompatActivity() {
    private lateinit var ipV4:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_client)
        getPreference()
        val addClient = findViewById<Button>(R.id.addnClient)
        addClient.setOnClickListener {
            addClientt()
        }
    }
    private fun getPreference() {
        val pref = getSharedPreferences("config", Context.MODE_PRIVATE)
        ipV4 = pref.getString("ip","0").toString()
    }
    //Crear un nuevo cliente
    private fun addClientt() {
        val idCardC: EditText = findViewById(R.id.idCardC)
        val nameC: EditText = findViewById(R.id.nameC)
        val eMailC: EditText = findViewById(R.id.eMailC)
        val passwC: EditText = findViewById(R.id.passwC)
        val ageClient: EditText = findViewById(R.id.ageClient)
        val queue = Volley.newRequestQueue(this)
        val url ="http://$ipV4:8081/API_REST_BD_CON/admin/clients/createclient.php"
        val result = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener<String> { response ->
                var intent:Intent?
                intent= Intent(this, FondMain::class.java)
                startActivity(intent)
            },Response.ErrorListener{ error ->
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val paramss = HashMap<String,String>()
                paramss.put("idCard",idCardC?.text.toString())
                paramss.put("name",nameC?.text.toString())
                paramss.put("eMail",eMailC?.text.toString())
                paramss.put("passw",passwC?.text.toString())
                paramss.put("age",ageClient?.text.toString())
                return paramss
            }
        }
        queue.add(result)
    }
}