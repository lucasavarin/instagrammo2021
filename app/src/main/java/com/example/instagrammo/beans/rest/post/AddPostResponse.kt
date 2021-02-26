package com.example.instagrammo.beans.rest.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddPostResponse (
    @Expose
    @SerializedName("result") val result : Boolean?
)