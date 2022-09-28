package com.example.myapplication.modelRWAdapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListInvestE
import com.example.myapplication.R

class ListALInvest(invest: MutableList<ListInvestE>) : RecyclerView.Adapter<ListALInvest.ViewHolder>() {
    val investt = invest

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listivest, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return investt.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.name.text=investt.get(i).nameIvest
        holder.invested.text=investt.get(i).valInves
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var invested: TextView

        init {
            name = itemView.findViewById(R.id.ListNameClientB)
            invested = itemView.findViewById(R.id.investedClient)
        }
    }
}
