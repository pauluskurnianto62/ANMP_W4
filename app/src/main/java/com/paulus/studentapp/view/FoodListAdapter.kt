package com.paulus.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.paulus.studentapp.databinding.FoodListItemBinding
import com.paulus.studentapp.databinding.StudentListItemBinding
import com.paulus.studentapp.model.Food
import com.paulus.studentapp.model.Student
import com.squareup.picasso.Picasso

class FoodListAdapter(val foodList:ArrayList<Food>): RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {
    class FoodViewHolder(var binding: FoodListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = FoodListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val images = foodList[position].images
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception -> exception.printStackTrace() }
        builder.build().load(images).into(holder.binding.imgFood)
        holder.binding.txtName.text = foodList[position].name
        holder.binding.txtType.text = foodList[position].type
        holder.binding.txtIngredient.text = foodList[position].ingredients.toString()
        holder.binding.txtCalories.text = foodList[position].nutrition_facts.calories.toString()
        holder.binding.txtFats.text = foodList[position].nutrition_facts.fat.toString()
        holder.binding.txtCarbs.text = foodList[position].nutrition_facts.carbs.toString()
        holder.binding.txtProteins.text = foodList[position].nutrition_facts.protein.toString()
    }

    fun updateFoodList(newFoodList: ArrayList<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}