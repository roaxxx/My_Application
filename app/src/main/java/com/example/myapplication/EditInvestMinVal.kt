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

class EditInvestMinVal : AppCompatActivity() {
    private lateinit var eMail: String
    private lateinit var valInv:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_invest_min_val)
        val bundle = intent.extras
        val twVal = findViewById<TextView>(R.id.twtMoney)
        val edtValM = findViewById<EditText>(R.id.edtValTM)
        val btnUpNI = findViewById<Button>(R.id.btnAddMI)
        eMail = bundle?.getString("eMail").toString()
        valInv = bundle?.getString("valInv").toString()
        twVal.text = valInv
        btnUpNI.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "http://192.168.10.17:8081/API_REST_BD_CON/investiment/updateMinVal.php"
            val result = object : StringRequest(
                Request.Method.POST, url,
                Response.Listener<String> { response ->
                    Toast.makeText(this, "$response", Toast.LENGTH_LONG).show()
                    var intent: Intent?
                    intent = Intent(this, Invest::class.java)
                    intent!!.putExtra("user", eMail)
                    startActivity(intent)
                }, Response.ErrorListener { error ->
                    Toast.makeText(this, "Error en la conexión", Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val paramss = HashMap<String, String>()
                    paramss.put("vslInv", edtValM?.text.toString())
                    paramss.put("eMail", eMail)
                    return paramss
                }
            }
            queue.add(result)
        }
    }
}