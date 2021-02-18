package com.costa.servizi

import com.costa.beans.Profile
import com.google.gson.annotations.SerializedName

data class ProfileEditRequest(
    @SerializedName("profileId")
    val profileId: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("picture")
    val picture: String?
)