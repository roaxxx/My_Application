package com.example.myapplication.modelRWAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListCAE
import com.example.myapplication.R

class ListAAShowClient(sClient:MutableList<ListCAE>) : RecyclerView.Adapter<ListAAShowClient.ViewHolder>() {
    val sClient = sClient

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_show_clients, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return sClient.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.name.text=sClient.get(i).getCName()
        holder.invested.text=sClient.get(i).gettInvest()
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var invested: TextView

        init {
            name = itemView.findViewById(R.id.name)
            invested = itemView.findViewById(R.id.value)
        }
    }
}