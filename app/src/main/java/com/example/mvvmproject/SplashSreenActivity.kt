package com.example.mvvmproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashSreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_sreen)

       val auth= FirebaseAuth.getInstance()
        val currentUser=auth.currentUser
        Handler(Looper.getMainLooper()).postDelayed({

            if (currentUser!=null){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }

        }, 3000)
    }
}