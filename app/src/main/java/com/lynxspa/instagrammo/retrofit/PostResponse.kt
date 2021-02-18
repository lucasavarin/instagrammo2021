package com.lynxspa.instagrammo.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostResponse (
    @Expose @SerializedName("result") val result: Boolean,
    @Expose @SerializedName("payload") val payload: List<Post>?
)