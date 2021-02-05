package com.example.instagrammo.bean

import com.google.gson.annotations.SerializedName

data class AuthRequest (
    @SerializedName ("username") var username : String,
    @SerializedName ("password") var password : String
)