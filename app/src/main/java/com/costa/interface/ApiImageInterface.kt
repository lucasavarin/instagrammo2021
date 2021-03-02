package com.costa.`interface`

import com.costa.beans.rest.PicSumImageOut
import retrofit2.Call
import retrofit2.http.GET

interface ApiImageInterface {
    @GET("v2/list/")
    fun getPictures(): Call<Array<PicSumImageOut>>
}