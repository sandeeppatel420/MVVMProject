package com.example.mvvmproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mvvmproject.databinding.ActivityProductDetailsBinding

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityProductDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val imageUri=intent.getStringExtra("image")
        val nameData=intent.getStringExtra("name")
        val priceData=intent.getStringExtra("price")
        val descriptionData=intent.getStringExtra("description")

        Glide.with(this).load(imageUri).into(binding.imageDetails)
        binding.nameDetails.text=nameData
        binding.priceDetails.text=priceData
        binding.descriptionDetails.text=descriptionData
    }
}