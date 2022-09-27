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
        val addClients = findViewById<Button>(R.id.addClients)
        val showClient = findViewById<Button>(R.id.showClients)
        val addInvest = findViewById<Button>(R.id.addInvest)
        val showInvest = findViewById<Button>(R.id.showInvest)
        var tFont=findViewById<TextView>(R.id.tFont)
        var numClients=findViewById<TextView>(R.id.numClients)
        var numInvest=findViewById<TextView>(R.id.numInvest)
        //Conexión a MySQL
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.14:8081/API_REST_BD_CON/fontDetail.php"
        Toast.makeText(this,url, Toast.LENGTH_LONG).show()
        val jsRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { response ->
                Toast.makeText(this,"Este", Toast.LENGTH_LONG).show()
                tFont.text=response.getString("tMoney")
                numClients.setText(response.getString("clients"))
                numInvest.setText(response.getString("investss"))
            }, {error->
                Toast.makeText(this,"$error ahhh", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest)

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
}