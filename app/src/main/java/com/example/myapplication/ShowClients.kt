package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ShowClients : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_clients)
        val scAddClients = findViewById<Button>(R.id.siAddInvest)
        scAddClients.setOnClickListener{
            startActivity(Intent(this,add_Client::class.java))
        }
    }
}