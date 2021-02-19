package com.example.instagrammo.beans.rest.profile

import com.example.instagrammo.beans.rest.profile.Profile
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProfileResponse (
    @Expose @SerializedName("result") val result : Boolean?,
    @Expose @SerializedName("payload") val payload : List<Profile>?
) : Serializable
