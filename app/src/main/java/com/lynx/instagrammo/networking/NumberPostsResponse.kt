package com.lynx.instagrammo.networking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NumberPostsResponse(

        @Expose
        @SerializedName("result")
        val resultT: Boolean?,

        @Expose
        @SerializedName("payload")
        val payload: Int?
)
