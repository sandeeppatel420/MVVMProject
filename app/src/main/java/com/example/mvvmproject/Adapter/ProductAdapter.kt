package com.example.mvvmproject.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.mvvmproject.DataModel.ProductModel
import com.example.mvvmproject.ProductDetailsActivity
import com.example.mvvmproject.R

class ProductAdapter(private val myContext: Context, private val productList: ArrayList<ProductModel>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
       val myView=LayoutInflater.from(myContext).inflate(R.layout.item_view1,parent,false)
       return ProductViewHolder(myView)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product=productList[position]
        Glide.with(myContext).load(product.image).into(holder.imageView)
        holder.textName.text=product.name
        holder.priceName.text=product.name
        holder.descriptionName.text=product.price

        holder.itemView.setOnClickListener {
         val intent=Intent(myContext,ProductDetailsActivity::class.java)
            intent.putExtra("image",product.image)
            intent.putExtra("name",product.name)
            intent.putExtra("price",product.price)
            intent.putExtra("description",product.description)
            intent.putExtra("id",product.id)
            myContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    class ProductViewHolder(itemView: View):ViewHolder(itemView){
       val imageView =itemView.findViewById<ImageView>(R.id.image_ImageView)!!
       val priceName=itemView.findViewById<TextView>(R.id.price_TextView)!!
       val textName=itemView.findViewById<TextView>(R.id.name_TextView)!!
       val descriptionName=itemView.findViewById<TextView>(R.id.description_TextView)!!
    }
}