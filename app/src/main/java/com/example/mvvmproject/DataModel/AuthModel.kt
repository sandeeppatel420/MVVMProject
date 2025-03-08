package com.example.mvvmproject.DataModel

data class AuthModel(
    val name:String?=null,
    val email:String?=null,
    val imageProfile:String?=null)

data class ProductModel(
    val image:String?=null,
    val id:String?=null,
    val name:String?=null,
    val price:String?=null,
    val description:String?=null
)