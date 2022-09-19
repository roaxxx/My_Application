package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class show_Invest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_invest)
        val siAddInvest = findViewById<Button>(R.id.siAddInvest)
        siAddInvest .setOnClickListener{
            startActivity(Intent(this,add_invest::class.java))
        }
    }
}