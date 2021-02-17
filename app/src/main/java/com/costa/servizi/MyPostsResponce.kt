package com.costa.servizi

import com.costa.beans.MyPosts
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MyPostsResponce (
    @Expose
    @SerializedName("payload")
    val payload: List<MyPosts>?
)