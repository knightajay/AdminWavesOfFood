package com.example.adminwavesoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import com.example.adminwavesoffood.databinding.ActivityAdminprofileBinding

class AdminprofileActivity : AppCompatActivity() {
private val binding :ActivityAdminprofileBinding by lazy {
    ActivityAdminprofileBinding.inflate(layoutInflater)
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backimage.setOnClickListener {
            finish()
        }
    }
}