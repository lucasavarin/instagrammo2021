package com.example.instagrammo.beans.rest.lorem

import com.example.instagrammo.beans.ResponseDataModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoremRest(
    @Expose @SerializedName("id") val id : String?,
    @Expose @SerializedName("author") val author : String?,
    @Expose @SerializedName("width") val width : String?,
    @Expose @SerializedName("height") val height : String?,
    @Expose @SerializedName("url") val url : String?,
    @Expose @SerializedName("download_url") val download_url : String?
) : ResponseDataModel