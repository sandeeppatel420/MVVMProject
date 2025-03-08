package com.example.mvvmproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject.Adapter.ProductAdapter
import com.example.mvvmproject.AllViewModel.ProductViewModel
import com.example.mvvmproject.DataModel.ProductModel

class HomeFragment : Fragment() {
    private lateinit var productRecyclerVie:RecyclerView
    private lateinit var dataList:ArrayList<ProductModel>
    private lateinit var productAdapter: ProductAdapter
    private lateinit var viewModel:ProductViewModel
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val myView=LayoutInflater.from(requireContext()).inflate(R.layout.fragment_home, container, false)
        productRecyclerVie=myView.findViewById(R.id.recyclerView)
        productRecyclerVie.layoutManager=GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)

        dataList= ArrayList()
        productAdapter=ProductAdapter(requireContext(),dataList)
        productRecyclerVie.adapter=productAdapter

        viewModel=ViewModelProvider(this)[ProductViewModel::class.java]
        viewModel.getProductAll()

        viewModel.productListLiveData.observe(viewLifecycleOwner){
         dataList.clear()
         dataList.addAll(it)
         productAdapter.notifyDataSetChanged()
        }
        return myView
    }


}