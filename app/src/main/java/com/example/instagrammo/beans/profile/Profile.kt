package com.example.instagrammo.beans.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProfileResponse (
    @Expose @SerializedName("result") val profileId : String?,
    @Expose @SerializedName("payload") val payload : List<Profile>?
) : Serializable

data class Profile (
    @Expose @SerializedName("profileId") val profileId : String?,
    @Expose @SerializedName("name") val name : String?,
    @Expose @SerializedName("description") val description : String?,
    @Expose @SerializedName("picture") val picture : String?,
    @Expose @SerializedName("followersNumber") val followersNumber : String?,
    @Expose @SerializedName("postsNumber") val postsNumber : String?
) : Serializable

data class EditProfile (
    @Expose @SerializedName("result") val result : Boolean?
        ) : Serializable