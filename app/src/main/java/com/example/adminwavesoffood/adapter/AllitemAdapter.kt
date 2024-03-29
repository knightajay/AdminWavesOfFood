package com.example.adminwavesoffood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminwavesoffood.databinding.ActivityAddItemBinding
import com.example.adminwavesoffood.databinding.ItemItemBinding

class AllitemAdapter(private val MenuItemname:ArrayList<String>,private val MenuItemprice:ArrayList<String>,private val MenuItemimage:ArrayList<Int>): RecyclerView.Adapter<AllitemAdapter.AddItemViewHolder>() {

    private val itemQuantities = IntArray(MenuItemname.size) {
        1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = MenuItemname.size
    inner class AddItemViewHolder(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                foodname.text = MenuItemname[position]
                foodprice.text = MenuItemprice[position]
                foodImageView.setImageResource(MenuItemimage[position])
                quantitytextview.text = quantity.toString()
                minusbutton.setOnClickListener {
                    decreaseQuantites(position,binding)
                }
                plusbutton.setOnClickListener {
                    increaseQuantites(position,binding)
                }
                deletebutton.setOnClickListener {
                    deleteQuantites(position,binding)
                }
            }
        }

    }

    private fun decreaseQuantites(position: Int, binding: ItemItemBinding) {
        if (itemQuantities[position] > 1) {
            itemQuantities[position]--
            binding.quantitytextview.text = itemQuantities[position].toString()
        }
    }

    private fun increaseQuantites(position: Int, binding: ItemItemBinding) {
        if (itemQuantities[position] < 10) {
            itemQuantities[position]++
            binding.quantitytextview.text = itemQuantities[position].toString()
        }
    }

    private fun deleteQuantites(position: Int, binding: ItemItemBinding) {
        MenuItemname.removeAt(position)
        MenuItemprice.removeAt(position)
        MenuItemimage.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, MenuItemname.size)
    }

}