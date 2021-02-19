package com.example.instagrammo.beans.rest.profile.edit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EditProfileResponse (
    @Expose @SerializedName("result") val result : Boolean?
) : Serializable