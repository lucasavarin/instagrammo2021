package com.costa.beans

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PicSumImageOut (
    @Expose
    @SerializedName("id")
    val id: String?,
    @Expose
    @SerializedName("author")
    val author: String?,
    @Expose
    @SerializedName("width")
    val width: Int?,
    @Expose
    @SerializedName("height")
    val height: Int?,
    @Expose
    @SerializedName("url")
    val url: String?,
    @Expose
    @SerializedName("download_url")
    val download_url: String?)