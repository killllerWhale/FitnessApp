package com.example.fitnessapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.model.FoodRecomModel
import kotlinx.android.synthetic.main.item_food_recommendation_layout.view.*

class FoodRecomAdapter: RecyclerView.Adapter<FoodRecomAdapter.FoodViewHolder>() {

    private var foodList = emptyList<FoodRecomModel>()

    class FoodViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_recommendation_layout, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.itemView.tv_name.text = foodList[position].name
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FoodRecomModel>){
        foodList = list
        notifyDataSetChanged()
    }
}