package com.example.fitnessapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.model.TraningModel
import kotlinx.android.synthetic.main.item_traning_layout.view.*

class TraningAdapter: RecyclerView.Adapter<TraningAdapter.TraningViewHolder>() {

    private var traninfList = emptyList<TraningModel>()

    class TraningViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraningViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_traning_layout, parent, false)
        return TraningViewHolder(view)
    }

    override fun onBindViewHolder(holder: TraningViewHolder, position: Int) {
        holder.itemView.tv_name.text = traninfList[position].name
        holder.itemView.tv_kkal.text = traninfList[position].kkal
        holder.itemView.tv_koll.text = traninfList[position].koll
    }

    override fun getItemCount(): Int {
        return traninfList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<TraningModel>){
        traninfList = list
        notifyDataSetChanged()
    }
}