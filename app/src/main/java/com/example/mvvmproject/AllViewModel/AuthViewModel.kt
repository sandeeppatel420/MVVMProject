package com.example.mvvmproject.AllViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmproject.DataModel.AuthModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel:ViewModel() {
     val users=MutableLiveData<AuthModel>()
     val auth=FirebaseAuth.getInstance()
     val db=FirebaseDatabase.getInstance()

     fun register(name: String, email: String, password: String) {
          auth.createUserWithEmailAndPassword(email, password)
               .addOnSuccessListener {
                    val user = auth.currentUser
                    if (user != null) {
                         users.value = AuthModel(name, email)
                         val data = HashMap<String, Any>()
                         data["name"] = name
                         data["email"] = email
                         data["imageProfile"] = "https://firebasestorage.googleapis.com/v0/b/onlyrealtimedata.appspot.com/o/image%2Fprofile1.png?alt=media&token=183f368e-8d5a-4ab6-bb16-8e6ad657ec57"

                         db.getReference("MvvmMy").child(user.uid).setValue(data)
                    }
               }
               .addOnFailureListener {
                    // Error handle karo (Toast ya Log)
               }
     }

     fun login(email: String,password: String){
          auth.signInWithEmailAndPassword(email,password)
               .addOnSuccessListener {
                    val user=auth.currentUser
                    users.value=AuthModel(user?.displayName?:"",user?.email?:"")
               }
     }

}