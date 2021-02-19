package com.example.instagrammo.beans.rest.follower

import com.example.instagrammo.beans.ResponseDataModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FollowerRest (
    @Expose @SerializedName("id") val id : String?,
    @Expose @SerializedName("name") val name : String?,
    @Expose @SerializedName("description") val description: String?,
    @Expose @SerializedName("picture") val picture: String?
) : ResponseDataModel
