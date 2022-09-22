package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLog = findViewById<Button>(R.id.btnLog)
        val user:EditText = findViewById(R.id.userName)
         val passw:EditText =findViewById(R.id.passw)

        btnLog.setOnClickListener{
            val url ="http://192.168.10.14/API_REST_BD_CON/insert.php"
            val queue= Volley.newRequestQueue(this)
            Toast.makeText(this,url,Toast.LENGTH_LONG).show()
            Toast.makeText(this,user.text.toString(),Toast.LENGTH_LONG).show()
            var result = object : StringRequest(
                Request.Method.POST,url,
            Response.Listener<String> { response ->
            },Response.ErrorListener{ error ->
                    Toast.makeText(this, "error $error", Toast.LENGTH_LONG).show()
                }){
                override fun getParams(): MutableMap<String, String>? {
                    val paramss = HashMap<String,String>()
                    paramss.put("name",user?.text.toString())
                    paramss.put("pasw",passw?.text.toString())
                    return paramss
                }
            }
            queue.add(result)
        }
    }
}