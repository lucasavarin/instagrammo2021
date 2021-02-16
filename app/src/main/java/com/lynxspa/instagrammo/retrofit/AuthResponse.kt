package com.lynxspa.instagrammo.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse (
    @Expose @SerializedName( "authToken") val authToken: String?,
    @Expose @SerializedName("profileId") val profileId: String?


    )

