package com.example.instagrammo.beans.rest.follower

import com.example.instagrammo.beans.rest.follower.FollowerRest
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FollowersResponse (
    @Expose @SerializedName("result") val result : Boolean?,
    @Expose @SerializedName("payload") val payload : List<FollowerRest>?
) : Serializable

