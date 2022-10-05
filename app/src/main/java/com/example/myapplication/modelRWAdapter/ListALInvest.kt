package com.example.myapplication.modelRWAdapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ModelDAO.ListInvestE
import com.example.myapplication.R

class ListALInvest(invest: MutableList<ListInvestE>,
                   private val onClickListener:(Int) -> Unit,
                    private val onClickDelete:(Int) -> Unit)
                    : RecyclerView.Adapter<ListALInvest.ViewHolder>() {
    val investt = invest

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listivest,
                                            parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return investt.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.render(investt.get(i),onClickListener,onClickDelete)
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ListNameClientB = itemView.findViewById<TextView>(R.id.ListNameClientB)
        val investedClient = itemView.findViewById<TextView>(R.id.investedClient)
        val addMoney = itemView.findViewById<ImageButton>(R.id.showID)
        val withdraw = itemView.findViewById<ImageButton>(R.id.DeleteI)
        fun render(ListInvestE:ListInvestE,
                   onClickListener: (Int) -> Unit,
                   onClickDelete: (Int) -> Unit) {
                ListNameClientB.text=ListInvestE.nameIvest
                investedClient.text=ListInvestE.valInvest
            addMoney.setOnClickListener{
                onClickListener(adapterPosition)
            }
            withdraw.setOnClickListener{
                onClickDelete(adapterPosition)
            }
        }
    }
}
