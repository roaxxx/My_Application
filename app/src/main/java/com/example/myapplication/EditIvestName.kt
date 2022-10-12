package com.example.myapplication

import android.content.Context
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

class EditIvestName : AppCompatActivity() {
    private lateinit var eMail: String
    private lateinit var nameIn:String
    private lateinit var ipV4:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_ivest_name)
        getPreference()
        val bundle= intent.extras
        val twNameI = findViewById<TextView>(R.id.twtMoney)
        val btnUpNI = findViewById<Button>(R.id.btnAddMI)
        eMail = bundle?.getString("eMail").toString()
        nameIn = bundle?.getString("nameI").toString()
        twNameI.text=nameIn
        btnUpNI.setOnClickListener {
            editName()
        }
    }
    private fun getPreference() {
        val pref = getSharedPreferences("config", Context.MODE_PRIVATE)
        ipV4 = pref.getString("ip","0").toString()
    }
    private fun editName() {
        val edtNameI = findViewById<EditText>(R.id.edtValTM)
        val queue = Volley.newRequestQueue(this)
        val url ="http://$ipV4:8081/API_REST_BD_CON/investiment/updateName.php"
        val result = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "$response", Toast.LENGTH_LONG).show()
                var intent: Intent?
                intent= Intent(this, Invest::class.java)
                intent!!.putExtra("user", eMail)
                startActivity(intent)
            }, Response.ErrorListener{ error ->
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val paramss = HashMap<String,String>()
                paramss.put("nNameI",edtNameI?.text.toString())
                paramss.put("eMail",eMail)
                return paramss
            }
        }
        queue.add(result)
    }
}