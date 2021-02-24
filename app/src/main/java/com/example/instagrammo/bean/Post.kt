package com.example.instagrammo.bean

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    val profileId: String,
    val postId: String?,
    val title: String,
    val picture: String,
    val uploadTime: String,
    val profile: Profile

)


