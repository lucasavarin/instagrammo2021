package com.example.instagrammo.request

import com.google.gson.annotations.SerializedName

data class EditProfileRequest (
    @SerializedName("profileId") val profileId: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("picture") val picture: String?
)