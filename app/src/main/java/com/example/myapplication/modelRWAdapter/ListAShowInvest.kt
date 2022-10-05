package com.example.myapplication.modelRWAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListIAnvestE
import com.example.myapplication.R

class ListAShowInvest(sInvest:MutableList<ListIAnvestE>,
                      private val onClickListener:(Int) -> Unit,
                      private  val onClickDelete:(Int)->Unit):
                      RecyclerView.Adapter<ListAShowInvest.ViewHolder>() {
    val sInvest = sInvest

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.show_invest_admin, parent,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int = sInvest .size
    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.render(sInvest[i], onClickListener,onClickDelete)
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name=itemView.findViewById<TextView>(R.id.name)
        var invested=itemView.findViewById<TextView>(R.id.value)
        var showID = itemView.findViewById<ImageButton>(R.id.showID)
        var DeleteI = itemView.findViewById<ImageButton>(R.id.DeleteI)
        fun render(invest: ListIAnvestE, onClickListener: (Int) -> Unit,
                                        onClickDelete: (Int) -> Unit) {
            name.text=invest.nameI
            invested.text=invest.vInvested
            showID.setOnClickListener {onClickListener(adapterPosition)}
            DeleteI.setOnClickListener { onClickDelete(adapterPosition) }
        }
    }
}