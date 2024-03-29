package com.example.adminwavesoffood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.adminwavesoffood.databinding.ActivitySignBinding
import com.example.adminwavesoffood.model.usermodel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var username:String
    private lateinit var nameofResturent:String
    private lateinit var databse:DatabaseReference


    private  val binding:ActivitySignBinding by lazy{
        ActivitySignBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //intalize Firebase Auth
        auth=Firebase.auth
        //intailize Firebase Databse
        databse=Firebase.database.reference

        binding.createUserButton.setOnClickListener{
            //get text from Edittext
            username=binding.name.text.toString().trim()
            nameofResturent=binding.nameofResturent.text.toString().trim()
            email=binding.emailorphone.text.toString().trim()
            password=binding.password.text.toString().trim()
            if(username.isBlank()||nameofResturent.isBlank()|| email.isBlank()||password.isBlank()){
                Toast.makeText(this,"please fill all detail",Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }

        }
        binding.alredyhaveaacount.setOnClickListener{
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        val locationlist= arrayOf("Jaipur", "Odisha","Bundi","Sikar")
        val adapter=ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,locationlist)
        val autoCompleteTextView=binding.listoflocation
        autoCompleteTextView.setAdapter(adapter)
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            task->
            if(task.isSuccessful){
                Toast.makeText(this,"Account Created Succesfully",Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Account Creation Failed",Toast.LENGTH_SHORT).show()
                Log.d("Account","Create Account:Failure",task.exception)
            }
        }
    }
//save data into database
    private fun saveUserData() {
        username=binding.name.text.toString().trim()
        nameofResturent=binding.nameofResturent.text.toString().trim()
        email=binding.emailorphone.text.toString().trim()
        password=binding.password.text.toString().trim()
        val user=usermodel(username,nameofResturent,email,password)
        val userid=FirebaseAuth.getInstance().currentUser!!.uid
    //save user data Firebase Database
        databse.child("user").child(userid).setValue(user)
    }
}