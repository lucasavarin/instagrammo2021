package com.lynx.instagrammo.networking.API

import com.lynx.instagrammo.beanRest.PicsumImageRest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiPicsumInterface {

    @GET("/v2/list")
    fun getImage(@Query("page") page: Int,
                 @Query("limit") limit: Int): Call<Array<PicsumImageRest>>

}