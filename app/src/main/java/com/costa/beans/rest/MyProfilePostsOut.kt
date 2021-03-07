package com.costa.beans.rest

import com.costa.`interface`.RestModel

data class MyProfilePostsOut(
    val postId: String,
    val title: String,
    val picture: String,
    val uploadTime: String
): RestModel