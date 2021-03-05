package com.costa.`interface`

import com.costa.beans.rest.PicSumImageOut
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiImageInterface {
    @GET("v2/list/")
    fun getPictures( @Query("page") page:Int,
                     @Query("limit") limit:Int): Call<Array<PicSumImageOut>>
}