package com.example.adminwavesoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminwavesoffood.adapter.AllitemAdapter
import com.example.adminwavesoffood.databinding.ActivityAllitemItemBinding

class Allitem_item : AppCompatActivity() {
    private val binding:ActivityAllitemItemBinding by lazy {
        ActivityAllitemItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val menuFoodName= listOf("Burger","Sandwitch","Moomo","Item","litti","choka")
        val menuprice= listOf("$1","$4","$5","$1","$4","$5")
        val menuimage= listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
        )
        binding.backimage.setOnClickListener{
            finish()
        }
        val adapter=AllitemAdapter(ArrayList(menuFoodName),ArrayList(menuprice),
            ArrayList(menuimage)
        )
        binding.menurecyclerview.layoutManager=LinearLayoutManager(this)
        binding.menurecyclerview.adapter=adapter
    }
}