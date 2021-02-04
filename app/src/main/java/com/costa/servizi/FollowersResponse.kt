package com.costa.servizi

import com.costa.beans.Profilo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FollowersResponse(
        @Expose
        @SerializedName("authToken")
        val payload: List<Profilo>?
) {}
