package com.example.mvvmproject.Fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmproject.AllViewModel.AuthViewModel
import com.example.mvvmproject.AllViewModel.GetUserDataViewModel
import com.example.mvvmproject.LoginActivity
import com.example.mvvmproject.R
import com.example.mvvmproject.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    private lateinit var viewModel: GetUserDataViewModel
    private lateinit var datalist:ArrayList<AuthViewModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view=binding?.root

        binding?.userLogoutText?.setOnClickListener {
            logout()
        }

        datalist=ArrayList()

     viewModel=ViewModelProvider(this)[GetUserDataViewModel::class.java]
     viewModel.getUser()
     viewModel.users1.observe(viewLifecycleOwner){
       binding?.userNameText1?.text=it.name
       binding?.userEmailText1?.text=it.email
         Glide.with(requireContext())
             .load(it.imageProfile)
             .placeholder(R.drawable.ic_launcher_background)
             .error(R.drawable.edit_24)
             .into(binding?.profileImage!!)
     }
        return view
    }
    private fun logout(){
        val dialog= AlertDialog.Builder(requireContext())
        dialog.setTitle("Log Out")
        dialog.setMessage("Are you sure you want to logout?")
        dialog.setPositiveButton("Logout"){ b ,_->
            FirebaseAuth.getInstance().signOut()
            val intent= Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
            b.dismiss()
            Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT).show()
        }
        dialog.setNegativeButton("Cancle"){a,_->
            a.cancel()
            Toast.makeText(requireContext(), "Cancle", Toast.LENGTH_SHORT).show()
        }
        dialog.setCancelable(false)
        val alertDialog = dialog.create()

        alertDialog.show()


        val positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
        positiveButton.setTextColor(ContextCompat.getColor(requireContext(),
            R.color.healthTextColor
        ))

        val negativeButton=alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        negativeButton.setTextColor(ContextCompat.getColor(requireContext(),
            R.color.healthTextColor
        ))
    }
}