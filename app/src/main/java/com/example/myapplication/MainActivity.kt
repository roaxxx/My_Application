package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLog = findViewById<Button>(R.id.btnLog)
        val user:EditText = findViewById(R.id.userName)
        btnLog.setOnClickListener{
            if(user.text.toString().equals("admin")){
                startActivity(Intent(this,FondMain::class.java))
            }else if(user.text.toString().equals("invest")){
                startActivity(Intent(this,Invest::class.java))
            }else if(user.text.toString().equals("Cliente")){
                startActivity(Intent(this,clientActivity1::class.java))
            }
        }
    }
}