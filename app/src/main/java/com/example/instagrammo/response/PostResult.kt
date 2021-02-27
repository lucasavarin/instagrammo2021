package com.example.instagrammo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostResult (
    @Expose
    @SerializedName("result")
    val result: Boolean
)