package com.example.adminwavesoffood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adminwavesoffood.databinding.ActivityAddItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class add_item : AppCompatActivity() {
    //food item detail
    private  lateinit var foodname:String
    private  lateinit var foodprice:String
    private  lateinit var fooddescription:String
    private  lateinit var foodingredient:String
   // private  var foodimage:Uri?=null
   //forebae
    private lateinit var auth: FirebaseAuth
    private lateinit var database :FirebaseDatabase

    private val binding:ActivityAddItemBinding by lazy {
        ActivityAddItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // intialize firebase
        auth = FirebaseAuth.getInstance()
        //intializemdatabse
        database = FirebaseDatabase.getInstance()
        binding.additem.setOnClickListener {
            // get data from filed
            foodname = binding.Foodname.text.toString().trim()
            foodprice = binding.Foodprice.text.toString().trim()
            fooddescription = binding.fooddescription.text.toString().trim()
            foodingredient = binding.foodingredeient.text.toString().trim()
            if (!(foodname.isBlank() || fooddescription.isBlank() || foodprice.isBlank() || foodingredient.isBlank())) {
                uploaddata()
                Toast.makeText(this, "Item Add is successfull", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Fill all detail",Toast.LENGTH_SHORT).show()
            }
        }


        binding.selectimage.setOnClickListener{
           pickimage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.backimage.setOnClickListener{
            finish()
        }
    }

    private fun uploaddata() {

    }


    val pickimage:ActivityResultLauncher<PickVisualMediaRequest> =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri->
            if(uri!=null){
                binding.selectedimage.setImageURI(uri)
            }

        }
}