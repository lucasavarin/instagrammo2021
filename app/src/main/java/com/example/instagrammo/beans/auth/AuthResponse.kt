package com.example.instagrammo.beans.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthResponse (
    @Expose
    @SerializedName("authToken") val authToken : String?,
    @Expose
    @SerializedName("profileId") val profileId : String?,
    @Expose
    @SerializedName("result") val result : Boolean?
) : Serializable
