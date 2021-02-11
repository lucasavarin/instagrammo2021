package com.example.instagrammo.response

import com.example.instagrammo.Follower
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FollowerResponse(
    @Expose
    @SerializedName("result")
    val result: Boolean,
    @Expose
    @SerializedName("payload")
    val payload: List<Follower>?
)
