package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListALInvest: RecyclerView.Adapter<ListALInvest.ViewHolder>() {
    val nameML = arrayOf("5", "6","7","8")
    val investedML = arrayOf("13", "14","15","16 ")

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listivest, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return nameML.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.name.text=nameML[i]
        holder.invested.text=investedML[i]

    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var invested: TextView

        init {
            name = itemView.findViewById(R.id.ListNameInvestB)
            invested = itemView.findViewById(R.id.investedL)
        }
    }
}
