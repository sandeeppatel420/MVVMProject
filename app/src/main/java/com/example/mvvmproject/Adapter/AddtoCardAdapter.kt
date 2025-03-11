package com.example.mvvmproject.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.mvvmproject.DataModel.ProductModel
import com.example.mvvmproject.R
import com.google.firebase.database.FirebaseDatabase

class AddtoCardAdapter(private val myContext: Context, private val addList: ArrayList<ProductModel>):RecyclerView.Adapter<AddtoCardAdapter.AddtoCardViewHolde>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddtoCardViewHolde {
        val myView= LayoutInflater.from(myContext).inflate(R.layout.item_view2,parent,false)
        return AddtoCardViewHolde(myView)
    }

    override fun onBindViewHolder(holder: AddtoCardViewHolde, position: Int) {
       val addlist1=addList[position]
        Glide.with(myContext).load(addlist1.image).into(holder.imageView)
        holder.textName.text=addlist1.name
        holder.priceName.text=addlist1.name
        holder.descriptionName.text=addlist1.price

        holder.deleteItem.setOnClickListener {
//            deleteData(position,addlist1.id!!)
        }
    }

    override fun getItemCount(): Int {
       return addList.size
    }
    class AddtoCardViewHolde(itemView: View): ViewHolder(itemView){
        val imageView =itemView.findViewById<ImageView>(R.id.image_ImageView1)!!
        val priceName=itemView.findViewById<TextView>(R.id.price_TextView1)!!
        val textName= itemView.findViewById<TextView>(R.id.name_TextView1)!!
        val descriptionName=itemView.findViewById<TextView>(R.id.description_TextView1)!!
        val deleteItem=itemView.findViewById<ImageView>(R.id.deleteItem)!!
    }


//    private fun deleteData(position: Int, userId:String){
//        val dialog= AlertDialog.Builder(myContext)
//        dialog.setTitle("Alert")
//        dialog.setMessage("Do you want to delete")
//
//        dialog.setPositiveButton("yes"){a,_->
//            val db= FirebaseDatabase.getInstance()
//            db.getReference("AddToCard").child(userId).removeValue()
//                .addOnSuccessListener {
//                    addList.removeAt(position)
//                    notifyDataSetChanged()
//                    Toast.makeText(myContext, "Delete", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener {
//                    Toast.makeText(myContext, "Failed", Toast.LENGTH_SHORT).show()
//                }
//            a.dismiss()
//        }
//        dialog.setNegativeButton("No"){ b,_->
//            b.cancel()
//        }
//        dialog.setCancelable(false)
//        dialog.show()
//    }
}