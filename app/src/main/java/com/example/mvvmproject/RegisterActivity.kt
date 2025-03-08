package com.example.mvvmproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmproject.AllViewModel.AuthViewModel
import com.example.mvvmproject.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var userViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginText.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        userViewModel=ViewModelProvider(this)[AuthViewModel::class.java]
        binding.registerBtn.setOnClickListener {
            val name=binding.registerName.text.toString()
            val email=binding.registerEmail.text.toString()
            val password=binding.registerPassword.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
             userViewModel.register(name,email,password)
            }
        }
        userViewModel.users.observe(this) {
            if (it!=null) {
                Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}