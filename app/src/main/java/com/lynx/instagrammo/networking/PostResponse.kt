package com.lynx.instagrammo.networking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lynx.instagrammo.beanRest.PostRest

data class PostResponse(
        @Expose
        @SerializedName("payload")
        val payload: List<PostRest>?
)
