package com.lynx.instagrammo.networking

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
        @SerializedName("profileId") var profileId: String,
        @SerializedName("name") var name: String,
        @SerializedName("description") var description: String,
        @SerializedName("picture") var picture: String
)
