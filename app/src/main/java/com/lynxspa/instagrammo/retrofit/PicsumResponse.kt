package com.lynxspa.instagrammo.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PicsumResponse (
    @Expose @SerializedName("id")val id : Int?,
    @Expose @SerializedName("author")val author: String?,
    @Expose @SerializedName("width")val width : Int?,
    @Expose @SerializedName("height")val height : Int?,
    @Expose @SerializedName("url")val url : String?,
    @Expose @SerializedName("download_url")val downloadUrl : String?
)