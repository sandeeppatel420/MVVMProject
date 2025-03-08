package com.example.mvvmproject.AllViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmproject.DataModel.ProductModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductViewModel():ViewModel() {
   val errorLiveData=MutableLiveData<String> ()
   val productListLiveData=MutableLiveData<List<ProductModel>>()
   private val db=FirebaseDatabase.getInstance()

   fun getProductAll() {
     db.getReference("products")
         .addValueEventListener(object :ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
               val productList=ArrayList<ProductModel>()
               for (data in snapshot.children)  {
                val   product=data.getValue(ProductModel::class.java)
                 if (product!=null)  {
                     productList.add(product)
                     productListLiveData.value=productList
                 }
               }
             }

             override fun onCancelled(error: DatabaseError) {
                 errorLiveData.value = error.message
             }

         })
   }
}