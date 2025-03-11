package com.example.mvvmproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mvvmproject.Fragment.AddToCartFragment
import com.example.mvvmproject.Fragment.HomeFragment
import com.example.mvvmproject.Fragment.MyorderFragment
import com.example.mvvmproject.Fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_icon -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.card_icon-> {
                    loadFragment(AddToCartFragment())
                    true
                }
                R.id.myOrder_icon-> {
                    loadFragment(MyorderFragment())
                    true
                }
                R.id. profile_icon-> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> {
                   return@setOnItemSelectedListener false
                }
            }
        }

    }

    private  fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout_container, fragment)
        transaction.commit()
    }
    }