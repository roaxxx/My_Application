package com.example.myapplication.modelRWAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListIAnvestE
import com.example.myapplication.R

class ListAShowInvest(sInvest:MutableList<ListIAnvestE>) : RecyclerView.Adapter<ListAShowInvest.ViewHolder>() {
    val sInvest = sInvest

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.show_invest_admin, parent,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return sInvest .size
    }
    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.name.text=sInvest .get(i).nameI
        holder.invested.text=sInvest.get(i).vInvested
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