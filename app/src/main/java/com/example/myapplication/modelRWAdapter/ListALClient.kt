package com.example.myapplication.modelRWAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListClientE
import com.example.myapplication.R

class ListALClient(client: MutableList<ListClientE>): RecyclerView.Adapter<ListALClient.ViewHolder>() {
    val client = client

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.clientlistrecyclerview, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return client.size
    }
    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.name.text = client.get(i).iNamee
        holder.invested.text = client.get(i).iVal
    }
    // Elementos de la tupla
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.nameInv)
        var invested= itemView.findViewById<TextView>(R.id.valInvest)
        fun renden(){

        }
    }
}