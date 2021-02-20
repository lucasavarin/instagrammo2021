package com.lynxspa.instagrammo.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @Expose @SerializedName("result") val result: Boolean,
    // @Expose @SerializedName("count") val count: Int,
    @Expose @SerializedName("payload") val payload: List<Profile>?
)