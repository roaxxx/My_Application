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
        holder.name.text = client.get(i).getIName()
        holder.invested.text = client.get(i).getIVal()
    }
    // Elementos de la tupla
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var invested: TextView
        init {
            name = itemView.findViewById(R.id.ListNameClientB)
            invested = itemView.findViewById(R.id.investedClient)
        }
    }
}