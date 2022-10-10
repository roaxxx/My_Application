package com.example.myapplication.modelRWAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.Investimentss
import com.example.myapplication.R

class inAdapter (invest: MutableList<Investimentss>)
    : RecyclerView.Adapter<inAdapter.ViewHolder>() {
    val investt = invest

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): inAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.investlistadmin,
            parent, false
        )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return investt.size
    }

    override fun onBindViewHolder(holder: inAdapter.ViewHolder, i: Int) {
        holder.render(investt.get(i))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ListNameClientB = itemView.findViewById<TextView>(R.id.nameInv)
        val investedClient = itemView.findViewById<TextView>(R.id.valInvest)
        fun render(
            ListInvestE: Investimentss
        ) {
            ListNameClientB.text = ListInvestE.nameIvest
            investedClient.text = ListInvestE.valInvest
        }
    }
}