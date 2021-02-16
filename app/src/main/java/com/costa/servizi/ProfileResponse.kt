package com.costa.servizi

import com.costa.beans.PostOut
import com.costa.beans.ProfileOut
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @Expose
    @SerializedName("payload")
    val payload: List<ProfileOut>?
)