package com.lynx.instagrammo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lynx.instagrammo.bean.Follower

data class FollowerResponse(

        @Expose
        @SerializedName("payload")
        val payload: List<Follower>?

)
