package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class FondWithDr : AppCompatActivity() {
    private lateinit var tMoney:String
    private lateinit var edtValTM:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fond_with_dr)
        val bundle= intent.extras
        tMoney =bundle?.getString("tMoney").toString()
        //Se instancian los widgets de la vista
        val twtMoney = findViewById<TextView>(R.id.twtMoney)
        edtValTM = findViewById(R.id.edtValTM)
        val tMoneyInt = tMoney.toInt()
        twtMoney.text= tMoney
    }
    fun withdraw(view: View){

    }
}