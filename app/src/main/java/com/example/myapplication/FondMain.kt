package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class FondMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fond_main)
        getFontDetails()

        val addClients = findViewById<Button>(R.id.addClients)
        val showClient = findViewById<Button>(R.id.showClients)
        val addInvest = findViewById<Button>(R.id.addInvest)
        val showInvest = findViewById<Button>(R.id.showInvest)
        addClients.setOnClickListener{
            startActivity(Intent(this,add_Client::class.java))
        }
        addInvest.setOnClickListener{
            startActivity(Intent(this,add_invest::class.java))
        }
        showClient.setOnClickListener {
            startActivity(Intent(this,ShowClients::class.java))
        }
        showInvest.setOnClickListener {
            startActivity(Intent(this,show_Invest::class.java))
        }
    }
    //Obtiene los detalles del encabezado del fondo
    private fun getFontDetails() {
        val tFont=findViewById<TextView>(R.id.tFont)
        val numClients=findViewById<TextView>(R.id.numClients)
        val numInvest=findViewById<TextView>(R.id.numInvest)
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.17:8081/API_REST_BD_CON/admin/fontDetail.php"
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                tFont.text=response.getString("tMoney")
                numClients.text=response.getString("clients")
                numInvest.text=response.getString("investss")
            }, {error->
                Toast.makeText(this,"$error ahhh", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest)
    }
}