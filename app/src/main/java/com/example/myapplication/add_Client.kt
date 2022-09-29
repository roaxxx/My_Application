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

class add_Client : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_client)
        val addClient = findViewById<Button>(R.id.addnClient)
        val idCardC: EditText = findViewById(R.id.idCardC)
        val nameC: EditText = findViewById(R.id.nameC)
        val eMailC: EditText = findViewById(R.id.eMailC)
        val passwC: EditText = findViewById(R.id.passwC)
        val ageClient: EditText = findViewById(R.id.ageClient)
        addClient.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url ="http://192.168.10.14:8081/API_REST_BD_CON/admin/clients/createclient.php"
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
}