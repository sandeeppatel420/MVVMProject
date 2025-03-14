package com.example.mvvmproject.AllViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmproject.DataModel.AddToCartModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddTocardViewModel:ViewModel(){
   val addDataList=MutableLiveData<List<AddToCartModel>> ()
   val errorLiveData=MutableLiveData<String> ()

  fun addData(){
   val db=FirebaseDatabase.getInstance()
   db.getReference("AddToCard")
       .addValueEventListener(object:ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {
              val addList=ArrayList<AddToCartModel>()
               for (data in snapshot.children){
                   val   product=data.getValue(AddToCartModel::class.java)
                   if (product!=null)  {
                       addList.add(product)
                       addDataList.value=addList
                   }
               }
           }

           override fun onCancelled(error: DatabaseError) {
             errorLiveData.value=error.message
           }

       })
  }
}