package com.example.adminwavesoffood.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminwavesoffood.databinding.DeliveryitemBinding

class delivery_Adapter(private val customername:ArrayList<String>,private val moneystatus:ArrayList<String>): RecyclerView.Adapter<delivery_Adapter.DelevierViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DelevierViewHolder {
        val binding=DeliveryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DelevierViewHolder(binding)
    }



    override fun onBindViewHolder(holder: DelevierViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int =customername.size
    inner class DelevierViewHolder(private val binding: DeliveryitemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
binding.apply{
    CustomerName.text=customername[position]
    payementmethod.text=moneystatus[position]
    val colormap= mapOf(
        "Recivied" to   Color.GREEN,"NotRecivied" to Color.RED,"Pending" to Color.GRAY
    )
    payementmethod.setTextColor(colormap[moneystatus[position]]?:Color.BLACK)
    StatusColor.backgroundTintList= ColorStateList.valueOf(colormap[moneystatus[position]]?:Color.BLACK)
}
        }

    }

}