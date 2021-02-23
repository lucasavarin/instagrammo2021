package com.costa.servizi

import com.google.gson.annotations.SerializedName

data class AddPostRequestRest(
    @SerializedName("profileId")
    var profileId: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("picture")
    var picture: String?
)