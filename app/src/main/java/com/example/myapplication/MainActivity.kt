package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private lateinit var ipV4:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLog = findViewById<Button>(R.id.btnLog)
        val config = findViewById<Button>(R.id.config)
        getPreference()
        if(ipV4.equals("")){
            configServerAddress()
            getPreference()
        }
        //Mostrar Dialog para ingresar dirección ip
        config.setOnClickListener {
            configServerAddress()
            getPreference()
        }
        btnLog.setOnClickListener{
           logIn()
        }
    }
    private fun getPreference() {
        val pref = getSharedPreferences("config",Context.MODE_PRIVATE)
        ipV4 = pref.getString("ip","0").toString()
    }
    //Iniciar sesión
    private fun logIn(){
        val user:EditText = findViewById(R.id.userName)
        val passw:EditText =findViewById(R.id.passw)
        var roll:String? = null
        val queue = Volley.newRequestQueue(this)
        val url ="http://$ipV4:8081/API_REST_BD_CON/select.php"
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
    fun configServerAddress(){
        val builder = AlertDialog.Builder(this@MainActivity)
        val view = layoutInflater.inflate(R.layout.get_ip,null)
        //Pasando vista al builder
        builder.setView(view)
        //Creado dialog
        val dialog = builder.create()
        dialog.show()
        val ip = view.findViewById<EditText>(R.id.ipAddress)
        val btn = view.findViewById<Button>(R.id.btnIPad)
        btn.setOnClickListener {
            val pref = getSharedPreferences("config",Context.MODE_PRIVATE)
            val adm = pref.edit()
            adm.putString("ip", ip.text.toString())
            adm.commit()
            dialog.hide()
        }
    }
}