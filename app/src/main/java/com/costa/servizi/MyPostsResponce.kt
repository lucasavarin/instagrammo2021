package com.costa.servizi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MyPostsResponce (
    @Expose
    @SerializedName("payload")
    val payload: List<MyPostsResponce>?
)