package com.costa.servizi


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BooleanRespomce(
    @Expose
    @SerializedName("result")
    val result: Boolean?
)