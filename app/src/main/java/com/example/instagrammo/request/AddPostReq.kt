package com.example.instagrammo.request

import com.google.gson.annotations.SerializedName

data class AddPostReq(
    @SerializedName("profileId")
    var profileId: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("picture")
    var picture: String
)