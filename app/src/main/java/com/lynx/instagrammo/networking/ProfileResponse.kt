package com.lynx.instagrammo.networking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lynx.instagrammo.beanRest.ProfileRest

data class ProfileResponse(
        @Expose
        @SerializedName("payload")
        val payload: List<ProfileRest>?
)
