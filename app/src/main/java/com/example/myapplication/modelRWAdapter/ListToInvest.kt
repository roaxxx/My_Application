package com.example.myapplication.modelRWAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListIAnvestE
import com.example.myapplication.R

class ListToInvest (sInvest:MutableList<ListIAnvestE>,
                    private val onClickListener:(Int) -> Unit):
RecyclerView.Adapter<ListToInvest.ViewHolder>()  {
    val sInvest = sInvest

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_invest_add_money, parent,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int = sInvest .size

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.render(sInvest[i], onClickListener)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var nameInv=itemView.findViewById<TextView>(R.id.nameInv)
        var valInvest=itemView.findViewById<TextView>(R.id.valInvest)
        var btnInvest = itemView.findViewById<Button>(R.id.btnInvest)

        fun render(invest: ListIAnvestE, onClickListener: (Int) -> Unit) {
            nameInv.text=invest.nameI
            valInvest.text=invest.vInvested
            btnInvest.setOnClickListener { onClickListener(adapterPosition) }
        }
    }
}