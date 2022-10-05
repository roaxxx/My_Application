package com.example.myapplication.modelRWAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListCAE
import com.example.myapplication.R

class ListAAShowClient(
    sClient:MutableList<ListCAE>,
    private val onClickListener:(ListCAE) -> Unit,
    private val onClickDelete: (Int) -> Unit
)
    : RecyclerView.Adapter<ListAAShowClient.ViewHolder>() {
    val sClient = sClient
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_show_clients, parent,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int = sClient.size

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.render(sClient[i],onClickListener,onClickDelete)
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name=itemView.findViewById<TextView>(R.id.name)
        val invested=itemView.findViewById<TextView>(R.id.value)
        val btnSc = itemView.findViewById<ImageButton>(R.id.btnSc)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)
        fun render(ListCAE: ListCAE,
                   onClickListener: (ListCAE) -> Unit,
                   onClickDelete: (Int) -> Unit){
            name.text=ListCAE.cName
            invested.text=ListCAE.tInvest
            btnSc.setOnClickListener{
                onClickListener(ListCAE)
            }
            btnDelete.setOnClickListener{
                onClickDelete(adapterPosition)
            }
        }
    }
}