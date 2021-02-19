package com.example.instagrammo.beans.rest.post

import com.example.instagrammo.beans.rest.post.PostRest
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostsResponse (
    @Expose @SerializedName("result") val result : Boolean?,
    @Expose @SerializedName("payload") val payload : List<PostRest>?
) : Serializable
