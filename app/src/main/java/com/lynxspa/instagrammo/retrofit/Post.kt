package com.lynxspa.instagrammo.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Post (
    val profileId : String?,
    val postId : String?,
    val title : String?,
    val picture : String?,
    val uploadTime : String?,
    val profile : Profile?
)