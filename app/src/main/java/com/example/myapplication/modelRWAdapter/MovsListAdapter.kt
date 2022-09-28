package com.example.myapplication.modelRWAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.MovsListDao
import com.example.myapplication.R

class MovsListAdapter(movH: MutableList<MovsListDao>) : RecyclerView.Adapter<MovsListAdapter.ViewHolder>() {
    val movH= movH

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movs_h, parent,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return movH.size
    }
    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.textH.text=movH.get(i).movClient
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textH: TextView
        init {
            textH = itemView.findViewById(R.id.TextMov)
        }
    }
}