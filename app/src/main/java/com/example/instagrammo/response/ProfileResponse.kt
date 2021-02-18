package com.example.instagrammo.response

import com.example.instagrammo.bean.Post
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProfileResponse(
    @Expose
    @SerializedName("result")
    val result: Boolean,
    @Expose
    @SerializedName("payload")
    val payload: List<PayloadProfile>?
)