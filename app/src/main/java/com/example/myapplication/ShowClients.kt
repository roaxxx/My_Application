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
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.ModelDAO.ListCAE
import com.example.myapplication.modelRWAdapter.ListAAShowClient
import org.json.JSONException
import kotlin.properties.Delegates

class ShowClients : AppCompatActivity() {
    private var sClients = mutableListOf<ListCAE>()
    private lateinit var adapter:ListAAShowClient
    private lateinit var numClients:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_clients)
        val scAddClients = findViewById<Button>(R.id.siAddInvest)
        var tFont=findViewById<TextView>(R.id.tFont3)
        var numClients=findViewById<TextView>(R.id.numClients3)
        var numInvest=findViewById<TextView>(R.id.numInvest3)
        numClients=findViewById<TextView>(R.id.numClients3)
        //Conexión a MySQL
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.10.14:8081/API_REST_BD_CON/admin/fontDetail.php"
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
        scAddClients.setOnClickListener{
            startActivity(Intent(this,add_Client::class.java))
        }

        val url2 = "http://192.168.10.14:8081/API_REST_BD_CON/admin/clients/ashowclients.php"
        val jsRequest2 = JsonObjectRequest(
            Request.Method.GET,url2,null,
            { response ->
                try{
                    var jsonArray=response.getJSONArray("data")
                    for(i in 0 until jsonArray.length()){
                        var jsonObject=jsonArray.getJSONObject(i)
                        sClients.add(ListCAE(jsonObject.getString("id_card"),
                                            jsonObject.getString("name"),
                                            jsonObject.getString("tMoney")))
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
    //Función para inciar el recylcerView con los clientes
    fun setRecyclerView(sClients: MutableList<ListCAE>) {
        val recyclerView = findViewById<RecyclerView>(R.id.ListShowC)
        adapter = ListAAShowClient(
            sClients,
            onClickListener = {ListCAE -> onItemSelected(ListCAE)},
            onClickDelete = {position -> deleteSelected(position)})
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    private fun onItemSelected(ListCAE:ListCAE){
        Toast.makeText(this,ListCAE.cName,Toast.LENGTH_SHORT).show()
    }
    private fun deleteSelected(position:Int){
        Toast.makeText(this,sClients.get(position).idCard,Toast.LENGTH_SHORT).show()
        sClients.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}