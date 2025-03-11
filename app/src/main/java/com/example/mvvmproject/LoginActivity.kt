package com.example.mvvmproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmproject.AllViewModel.AuthViewModel
import com.example.mvvmproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerText.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }
        userViewModel= ViewModelProvider(this)[AuthViewModel::class.java]
        binding.loginBtn.setOnClickListener {
            val email=binding.loginEmail.text.toString()
            val password=binding.loginPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                userViewModel.login(email,password)
            }
        }

        userViewModel.users.observe(this) {
            if (it!=null) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}