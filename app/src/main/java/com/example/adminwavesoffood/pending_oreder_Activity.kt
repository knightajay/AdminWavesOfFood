
package com.example.adminwavesoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminwavesoffood.adapter.delivery_Adapter
import com.example.adminwavesoffood.adapter.pendingorder_Adapter
import com.example.adminwavesoffood.databinding.ActivityPendingOrederBinding
import com.example.adminwavesoffood.databinding.PendingorderItemBinding

class pending_oreder_Activity : AppCompatActivity() {
    private val binding:ActivityPendingOrederBinding by lazy {
        ActivityPendingOrederBinding.inflate(layoutInflater)
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
        val orderquantity= arrayListOf(
            "5","4","3"
        )
        val orderedfoofimage= arrayListOf(
            R.drawable.menu1,
            R.drawable.menu3,
            R.drawable.menu2,
        )
        val adapter =pendingorder_Adapter(customername,orderquantity,orderedfoofimage, this)
        binding.pendingrecyclerview.adapter=adapter
        binding.pendingrecyclerview.layoutManager= LinearLayoutManager(this)
    }
    }
