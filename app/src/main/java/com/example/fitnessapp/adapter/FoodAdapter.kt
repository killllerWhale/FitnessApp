package com.example.fitnessapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.model.FoodModel
import kotlinx.android.synthetic.main.item_food_layout.view.*
import kotlinx.android.synthetic.main.item_traning_layout.view.*
import kotlinx.android.synthetic.main.item_traning_layout.view.tv_kkal
import kotlinx.android.synthetic.main.item_traning_layout.view.tv_name

class FoodAdapter(private val onItemClickListener: (position: Int) -> Unit): RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private var foodList = emptyList<FoodModel>()

    class FoodViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_layout, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.itemView.tv_name.text = foodList[position].name
        holder.itemView.tv_kkal.text = foodList[position].kkal
        holder.itemView.tv_gram.text = foodList[position].gram
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FoodModel>){
        foodList = list
        notifyDataSetChanged()
    }
}