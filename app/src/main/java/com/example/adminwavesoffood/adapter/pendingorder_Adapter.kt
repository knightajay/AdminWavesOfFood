package com.example.adminwavesoffood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.adminwavesoffood.databinding.PendingorderItemBinding

class pendingorder_Adapter(private val customerNames:ArrayList<String>,private val quantity:ArrayList<String>,private val foodimage:ArrayList<Int>,private val context: Context):RecyclerView.Adapter<pendingorder_Adapter.pendingorderHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pendingorderHolder {
       val binding=PendingorderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return pendingorderHolder(binding)
    }



    override fun onBindViewHolder(holder: pendingorderHolder, position: Int) {
holder.bind(position)

    }

    override fun getItemCount(): Int =customerNames.size
    inner class pendingorderHolder(private val binding: PendingorderItemBinding) :RecyclerView.ViewHolder(binding.root){
        private var isAccepted=false
        fun bind(position: Int) {
            binding.apply {
                Nameofcustomer.text=customerNames[position]
                quantitycount.text=quantity[position]
                foodImageView.setImageResource(foodimage[position])
                Orderacceptedbutton.apply {
                    if(!isAccepted){
                        text="Acepted"
                    }else{
                        text="dispatch"
                    }
                    setOnClickListener{
                      if(!isAccepted){
                          text="dispatch"
                          isAccepted=true
                          shoToast("Order is Accepted")
                      }else{
customerNames.removeAt(adapterPosition)
                          notifyItemRemoved(adapterPosition)
                          shoToast("Order is Dispatch")
                      }
                    }
                }

            }

        }
      private  fun shoToast(message:String){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }
    }
}