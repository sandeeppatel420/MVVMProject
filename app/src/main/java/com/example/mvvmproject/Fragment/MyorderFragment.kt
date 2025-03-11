package com.example.mvvmproject.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvmproject.databinding.FragmentMyorderBinding


class MyorderFragment : Fragment() {
    private var binding: FragmentMyorderBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyorderBinding.inflate(inflater, container, false)
         val view=binding?.root



        return view
    }


}