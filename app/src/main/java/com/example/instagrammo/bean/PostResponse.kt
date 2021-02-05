package com.example.instagrammo.bean

import android.net.Uri
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostResponse (
    @Expose @SerializedName("result") val result : Boolean?,
    @Expose @SerializedName("payload") val payload : ArrayList<Post>?

) : Serializable

data class Post (
    @Expose @SerializedName("profileId") val profileId : String?,
    @Expose @SerializedName("postId") val postId : String?,
    @Expose @SerializedName("title") val title : String?,
    @Expose @SerializedName("picture") val picture : Uri?,
    @Expose @SerializedName("uploadTime") val uploadTime : String?,
    @Expose @SerializedName("profile") val profile : Profile?
) : Serializable

data class Profile(
    @Expose @SerializedName("profileId") val profileId : String?,
    @Expose @SerializedName("name") val name : String?,
    @Expose @SerializedName("description") val description : String?,
    @Expose @SerializedName("picture") val picture : Uri?,
    @Expose @SerializedName("followersNumber") val followersNumber : String?,
    @Expose @SerializedName("postsNumber") val postsNumber : String?
) : Serializable