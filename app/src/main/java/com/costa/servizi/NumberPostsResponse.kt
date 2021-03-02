package com.costa.servizi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NumberPostsResponse(
    @Expose
    @SerializedName("payload")
    val payload: Int?
)