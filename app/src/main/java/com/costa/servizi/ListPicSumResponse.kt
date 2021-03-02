package com.costa.servizi

import com.costa.beans.rest.PicSumImageOut
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListPicSumResponse(
    @Expose
    @SerializedName("payload")
    val payload: List<PicSumImageOut>?
)