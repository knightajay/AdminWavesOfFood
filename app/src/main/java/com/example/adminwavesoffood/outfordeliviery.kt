package com.example.adminwavesoffood

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminwavesoffood.adapter.delivery_Adapter
import com.example.adminwavesoffood.databinding.ActivityOutfordelivieryBinding

class outfordeliviery : AppCompatActivity() {
    private val binding:ActivityOutfordelivieryBinding by lazy{
        ActivityOutfordelivieryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backimage.setOnClickListener{
                finish()
        }
        val customername= arrayListOf(
            "Ajay","mota","bhai"
        )
        val moneystatus= arrayListOf(
            "Recivied","NotRecivied","Pending"
        )
        val adapter =delivery_Adapter(customername,moneystatus)
        binding.delevieryRecyclerviw.adapter=adapter
        binding.delevieryRecyclerviw.layoutManager=LinearLayoutManager(this)
    }
}