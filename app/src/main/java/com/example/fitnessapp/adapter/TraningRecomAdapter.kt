package com.example.fitnessapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.model.TraningRecomModel
import kotlinx.android.synthetic.main.item_traning_layout.view.*

class TraningRecomAdapter(private val onItemClickListener: (position: Int) -> Unit): RecyclerView.Adapter<TraningRecomAdapter.TraningRecomViewHolder>() {

    private var traninfRecomList = emptyList<TraningRecomModel>()

    inner class TraningRecomViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraningRecomViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_traning_recom_layout, parent, false)

        return TraningRecomViewHolder(view)
    }

    override fun onBindViewHolder(holder: TraningRecomViewHolder, position: Int) {
        holder.itemView.tv_name.text = traninfRecomList[position].name
        holder.itemView.tv_kkal.text = traninfRecomList[position].kkal
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(position)
        }
    }


    override fun getItemCount(): Int {
        return traninfRecomList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<TraningRecomModel>){
        traninfRecomList = list
        notifyDataSetChanged()
    }
}