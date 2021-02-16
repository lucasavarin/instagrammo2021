package com.example.instagrammo.beans.profile

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EditProfileRequest (
    @SerializedName("profileId") val profileId : String?,
    @SerializedName("name") val name : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("picture") val picture : String?
        )