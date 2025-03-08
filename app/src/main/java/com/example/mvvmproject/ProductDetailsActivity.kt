package com.example.mvvmproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mvvmproject.databinding.ActivityProductDetailsBinding
import com.google.firebase.database.FirebaseDatabase

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityProductDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val imageUri = intent.getStringExtra("image") ?: ""
        val nameData = intent.getStringExtra("name") ?: ""
        val priceData = intent.getStringExtra("price") ?: ""
        val descriptionData = intent.getStringExtra("description") ?: ""
        val id = intent.getStringExtra("id") ?: ""

        Glide.with(this).load(imageUri).into(binding.imageDetails)
        binding.nameDetails.text=nameData
        binding.priceDetails.text=priceData
        binding.descriptionDetails.text=descriptionData

        binding.addToCardBtn.setOnClickListener {
          insertData(nameData,priceData,id, descriptionData,imageUri)
        }
    }

    private fun insertData(name:String, price:String, id:String, descr:String, image:String){
       val db=FirebaseDatabase.getInstance()

       val data=HashMap<String,Any> ()
        data["image"]=image
        data["id"]=id
        data["name"]=name
        data["price"]=price
        data["description"]=descr

        db.getReference("AddToCard").child(id).setValue(data)
            .addOnSuccessListener {
                val fragment = AddToCartFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout_container, fragment) // 👈 fragment_container ko apne layout ke hisaab se replace karo
                    .addToBackStack(null)  // 👈 BackStack me add karo
                    .commit()
            }
            .addOnFailureListener {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
    }

}