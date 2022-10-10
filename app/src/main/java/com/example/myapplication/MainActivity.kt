package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLog = findViewById<Button>(R.id.btnLog)
        val user:EditText = findViewById(R.id.userName)
        val passw:EditText =findViewById(R.id.passw)
        var roll:String? = null
        btnLog.setOnClickListener{
            val queue = Volley.newRequestQueue(this)
            val url ="http://192.168.10.17:8081/API_REST_BD_CON/select.php"
            val result = object : StringRequest(
                Request.Method.POST,url,
                Response.Listener<String> { response ->
                    roll = response
                    var intent:Intent?
                    if(roll.equals("admin")){
                        intent=Intent(this, FondMain::class.java)
                    }else if(roll.equals("cliente")){
                        intent=Intent(this, clientActivity1::class.java)
                    }else if(roll.equals("invest")){
                        intent=Intent(this, Invest::class.java)
                    }else{
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                        intent=Intent(this, MainActivity::class.java)
                    }
                    intent.putExtra("user", user.text.toString())
                    startActivity(intent)
                },Response.ErrorListener{ error ->
                    Toast.makeText(this, "Sin conexión", Toast.LENGTH_LONG).show()
                }){
                override fun getParams(): MutableMap<String, String>? {
                    val paramss = HashMap<String,String>()
                    paramss.put("name",user?.text.toString())
                    paramss.put("passw",passw?.text.toString())
                    return paramss
                }
            }
            queue.add(result)
        }
    }
}