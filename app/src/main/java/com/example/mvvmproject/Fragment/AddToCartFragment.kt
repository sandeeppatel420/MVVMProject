package com.example.mvvmproject.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.Adapter.AddtoCardAdapter
import com.example.mvvmproject.AllViewModel.AddTocardViewModel
import com.example.mvvmproject.DataModel.AddToCartModel
import com.example.mvvmproject.R
import com.google.firebase.database.FirebaseDatabase


class AddToCartFragment : Fragment() ,AddtoCardAdapter.DeleteCartItem{
    private lateinit var cartRecyclerVie: RecyclerView
    private lateinit var dataList:ArrayList<AddToCartModel>
    private lateinit var productAdapter:AddtoCardAdapter
    private lateinit var viewModel: AddTocardViewModel
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val myView=LayoutInflater.from(requireContext()).inflate(R.layout.fragment_add_to_cart, container, false)
       cartRecyclerVie=myView.findViewById(R.id.cartRecyclerview)
       cartRecyclerVie.layoutManager= LinearLayoutManager(requireContext())
       dataList= ArrayList()
       productAdapter=AddtoCardAdapter(requireContext(),dataList,this)
       cartRecyclerVie.adapter=productAdapter

       viewModel=ViewModelProvider(this) [AddTocardViewModel::class.java]
       viewModel.addData()
       viewModel.addDataList.observe(viewLifecycleOwner){
           dataList.clear()
           dataList.addAll(it)
           productAdapter.notifyDataSetChanged()
       }
        return myView
    }

    override fun deleteItem(position: Int, data: AddToCartModel) {
        val db= FirebaseDatabase.getInstance()
            db.getReference("AddToCard").child(data.cartId.toString()).removeValue()
               .addOnSuccessListener {
                   dataList.removeAt(position)
                   productAdapter.notifyDataSetChanged()
                   Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show()
               }
               .addOnFailureListener {
                   Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
    }


}