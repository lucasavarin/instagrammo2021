package com.example.instagrammo.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostNumberResponse (
    @Expose
    @SerializedName("result")
    val result: Boolean,
    @Expose
    @SerializedName("payload")
    val payload: Int
)