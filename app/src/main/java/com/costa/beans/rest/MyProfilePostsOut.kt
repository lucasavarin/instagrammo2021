package com.costa.beans.rest

data class MyProfilePostsOut(
    val postId: String,
    val title: String,
    val picture: String,
    val uploadTime: String
)