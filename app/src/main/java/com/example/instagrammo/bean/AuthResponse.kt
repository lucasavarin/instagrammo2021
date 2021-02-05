package com.example.instagrammo.bean

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthResponse (
    @Expose
    @SerializedName("authToken") val authToken : String?,
    @Expose
    @SerializedName("profileId") val profileId : String?
) : Serializable