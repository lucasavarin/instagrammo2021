package com.example.instagrammo.bean

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    val profileId: String,
    val postId: String?,
    val title: String,
    val picture: String,
    val uploadTime: String,
    val profile: Profile

)


/*class Post (
    @Expose
    @SerializedName("profileId")
    val profileId: String,
    @Expose
    @SerializedName("postId")
    val postId: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("picture")
    val picture: String,
    @Expose
    @SerializedName("uploadTime")
    var uploadTime: String,
    @Expose
    @SerializedName("profile")
    var profile: Profile
    )
*/


/*class Post (
    @Expose
    @SerializedName("profileId")
    val profileId: String,
    @Expose
    @SerializedName("postId")
    val postId: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("picture")
    val picture: String,
    @Expose
    @SerializedName("uploadTime")
    var uploadTime: String,
    @Expose
    @SerializedName("profile")
    var profile: Profile
    )
*/