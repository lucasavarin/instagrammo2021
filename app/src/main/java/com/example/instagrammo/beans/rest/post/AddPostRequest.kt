package com.example.instagrammo.beans.rest.post

import com.google.gson.annotations.SerializedName

data class AddPostRequest(
    @SerializedName("profileId") var profileId: String,
    @SerializedName("title") var title: String,
    @SerializedName("picture") var picture: String
)