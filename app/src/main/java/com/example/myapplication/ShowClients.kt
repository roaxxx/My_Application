package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.ModelDAO.ListCAE
import com.example.myapplication.modelRWAdapter.ListAAShowClient
import org.json.JSONException

class ShowClients : AppCompatActivity() {
    private var sClients = mutableListOf<ListCAE>()
    private lateinit var adapter:ListAAShowClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_clients)
        getFontDetails()
        getFontClients()
        val scAddClients = findViewById<Button>(R.id.siAddInvest)
        scAddClients.setOnClickListener{
            startActivity(Intent(this,add_Client::class.java))
        }
    }
    //Obtiene el listado completo de clietnes
    private fun getFontClients() {
        val queue = Volley.newRequestQueue(this)
        val url2 = "http://192.168.10.17:8081/API_REST_BD_CON/admin/clients/ashowclients.php"
        val jsRequest2 = JsonObjectRequest(
            Request.Method.GET,url2,null,
            { response ->
                try{
                    val jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        val jsonObject=jsonArray.getJSONObject(i)
                        if(!jsonObject.getString("name").equals("null")){
                            sClients.add(ListCAE(
                                jsonObject.getString("e_mail"),
                                jsonObject.getString("name"),
                                jsonObject.getString("tMoney")))
                        }
                    }
                    setRecyclerView(sClients)
                }
                catch (e: JSONException){
                    Toast.makeText(this,"$e", Toast.LENGTH_LONG).show()
                }
            }, {error->
                Toast.makeText(this,"$error ahhh", Toast.LENGTH_LONG).show()
            })
        queue.add(jsRequest2)
    }
    //Obtiene los detalles del fondo
    private fun getFontDetails(){
        val tFont=findViewById<TextView>(R.id.tFont3)
        val numClients=findViewById<TextView>(R.id.numClients3)
        val numInvest=findViewById<TextView>(R.id.numInvest3)
        //Conexión a MySQL
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
    //Función para inciar el recylcerView con los clientes
    private fun setRecyclerView(sClients: MutableList<ListCAE>) {
        val recyclerView = findViewById<RecyclerView>(R.id.ListShowC)
        adapter = ListAAShowClient(
            sClients,
            onClickListener = {ListCAE -> onItemSelected(ListCAE)},
            onClickDelete = {position -> deleteSelected(position)})
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    //Obtiene como parámetro la posición seleccionada, para modificar cliente.
    private fun onItemSelected(ListCAE:ListCAE){
        val intent = Intent(this, Detail_Client::class.java)
        intent.putExtra("user", ListCAE.idCard)
        startActivity(intent)
    }
    //Obtiene como parámetro la posición seleccionada, para eliminar cliente.
    private fun deleteSelected(position:Int){
        val email = sClients[position].idCard
        DeleteClient(position,email)
        sClients.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
    //Elimina cliente seleccionado en base de datos
    private fun DeleteClient(position: Int, email: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.17:8081/API_REST_BD_CON/admin/clients/deleteclient.php"
        val result = object : StringRequest(
            Request.Method.POST,url,
            Response.Listener<String> { response ->
                Toast.makeText(this, "$response", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener{ error ->
                Toast.makeText(this, "Sin conexión", Toast.LENGTH_LONG).show()
            }){
            override fun getParams(): MutableMap<String, String>? {
                val paramss = HashMap<String,String>()
                paramss.put("name",email)
                return paramss
            }
        }
        queue.add(result)
    }
}