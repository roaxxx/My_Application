package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FondMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fond_main)
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
}