package com.costa.servizi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 data class AuthResponse (
    @Expose
    @SerializedName("authToken")
    val authToken: String?,

    @Expose
    @SerializedName("profileId")
    val profileId: String?

)
