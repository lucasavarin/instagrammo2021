package com.costa.servizi

import com.costa.beans.FollowerOut
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FollowersResponse(
        @Expose
        @SerializedName("payload")
        val payload: List<FollowerOut>?
) {}
