package com.example.instagrammo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PictureResponse (
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("author")
    val author: String,
    @Expose
    @SerializedName("width")
    val width: Number,
    @Expose
    @SerializedName("height")
    val height: Number,
    @Expose
    @SerializedName("url")
    var url: String,
    @Expose
    @SerializedName("download_url")
    var download_url: String
    )