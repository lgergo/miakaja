package com.example.miakaja.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.miakaja.databinding.IngredientLayoutBinding
import com.example.miakaja.fragment.HomeFragment
import com.example.miakaja.fragment.HomeFragmentDirections
import com.example.miakaja.model.Ingredient

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    //from nav_graph
    class IngredientViewHolder(val itemBinding: IngredientLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback=object:DiffUtil.ItemCallback<Ingredient>(){
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.ingredientId==newItem.ingredientId &&
                    oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientViewHolder {
        return IngredientViewHolder(
            IngredientLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val currentIngredient = differ.currentList[position]

        //map to layouts
        holder.itemBinding.name.text = currentIngredient.name

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToEditIngredientFragment(currentIngredient)
            it.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}