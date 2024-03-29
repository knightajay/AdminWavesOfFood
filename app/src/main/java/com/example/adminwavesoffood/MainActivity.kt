package com.example.adminwavesoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminwavesoffood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.AddMenu.setOnClickListener{
         val intent=Intent(this,add_item::class.java)
         startActivity(intent)
        }
binding.allitemMenu.setOnClickListener{
    val intent=Intent(this,Allitem_item::class.java)
    startActivity(intent)
}
        binding.outfordelevierybutton.setOnClickListener{
            val intent=Intent(this,outfordeliviery::class.java)
            startActivity(intent)
        }
        binding.profileview.setOnClickListener{
            val intent=Intent(this,AdminprofileActivity::class.java)
            startActivity(intent)
        }
        binding.createnewuser.setOnClickListener{
            val intent=Intent(this,CreateNewUser::class.java)
            startActivity(intent)
        }
        binding.pendingordertextview.setOnClickListener{
            val intent=Intent(this,pending_oreder_Activity::class.java)
            startActivity(intent)
        }
    }
}