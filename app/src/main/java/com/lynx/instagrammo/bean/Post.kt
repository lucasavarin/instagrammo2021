package com.lynx.instagrammo.bean

data class Post(
    val profileId: String,
    val postId: String,
    val title: String,
    val picture: String,
    val uploadTime: String,
    val profile: Profile
)
