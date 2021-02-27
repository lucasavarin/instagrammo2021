package com.example.instagrammo.beans.rest.post

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NumberPosts(
    @SerializedName("result") var result: Boolean,
    @SerializedName("payload") var payload: Int
): Serializable