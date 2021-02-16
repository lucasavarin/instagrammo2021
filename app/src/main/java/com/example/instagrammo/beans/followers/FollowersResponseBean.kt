package com.example.instagrammo.beans.followers

import com.example.instagrammo.beans.ResponseDataModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FollowersResponse (
    @Expose @SerializedName("result") val result : Boolean?,
    @Expose @SerializedName("payload") val payload : List<FollowerProfile>?
) : Serializable

data class FollowerProfile (
    @Expose @SerializedName("id") val id : String?,
    @Expose @SerializedName("name") val name : String?,
    @Expose @SerializedName("description") val description: String?,
    @Expose @SerializedName("picture") val picture: String?
) : ResponseDataModel
