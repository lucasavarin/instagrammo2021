package com.example.instagrammo.bean

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Profile (
    val profileId: String,
    var name: String,
    var description: String,
    val picture: String,
    val followersNumber: String,
    val postsNumber: String
)
