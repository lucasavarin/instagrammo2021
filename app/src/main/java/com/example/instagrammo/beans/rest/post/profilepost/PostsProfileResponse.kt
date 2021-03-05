package com.example.instagrammo.beans.rest.post.profilepost

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PostsProfileResponse (
        @Expose @SerializedName("result") val result : Boolean?,
        @Expose @SerializedName("payload") val payload : List<PostsProfileRest>?
) : Serializable
