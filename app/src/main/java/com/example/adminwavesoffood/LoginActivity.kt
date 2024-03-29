

package com.example.adminwavesoffood

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.adminwavesoffood.R
import com.example.adminwavesoffood.databinding.ActivityLoginBinding
import com.example.adminwavesoffood.model.usermodel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class LoginActivity : AppCompatActivity() {
    private var username: String? = null
    private var nameofResturent: String? = null
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googlesignInclient: GoogleSignInClient
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        //intailize Firebase Auth
        auth = Firebase.auth
        //intailizee Firebase Database
        database = Firebase.database.reference
//intialize Google Sign in
        googlesignInclient = GoogleSignIn.getClient(this, googleSignInOptions)
        binding.loginbutton.setOnClickListener {
            //get text from Edittext

            email = binding.email.text.toString().trim()
            password = binding.passwordd.text.toString().trim()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please Fill All Detail", Toast.LENGTH_SHORT).show()

            } else {
                createUserAcount(email, password)
            }
        }
        binding.googlebutton.setOnClickListener {
            val signIntent = googlesignInclient.signInIntent
            launcher.launch(signIntent)
        }
        binding.donthaveacount.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createUserAcount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(this, "Login sucessful", Toast.LENGTH_SHORT).show()
                updateUi(user)
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        saveuserData()
                        Toast.makeText(this, "Create User & Login SuccesFull", Toast.LENGTH_SHORT)
                            .show()
                        updateUi(user)
                    } else {
                        Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                        Log.d("Account", "createacount:Authentication Failed", task.exception)
                    }
                }
            }
        }
    }

    private fun saveuserData() {
        email = binding.email.text.toString().trim()
        password = binding.passwordd.text.toString().trim()
        val user = usermodel(username, nameofResturent, email, password)
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            database.child("user").child(it).setValue(user)
        }
    }



    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount = task.result
                   val credential=  GoogleAuthProvider.getCredential(account.idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { authTask ->
                        if (authTask.isSuccessful) {
                            //succesfully sign in with Google
                            Toast.makeText(
                                this,
                                "Suceesfully sing in with Google",
                                Toast.LENGTH_SHORT
                            ).show()
//
                          updateUi(authTask.result?.user)
                            finish()
                        } else {
                            Toast.makeText(this, "Google singn failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Google singn failed", Toast.LENGTH_SHORT).show()
                }

            }
        }
//         check if user is already login
 override fun onStart(){
        super.onStart()
        val currentUser=auth.currentUser
        if (currentUser!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    private fun updateUi(user: FirebaseUser?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}