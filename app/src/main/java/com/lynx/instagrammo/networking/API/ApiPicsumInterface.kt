package com.lynx.instagrammo.networking.API

import com.lynx.instagrammo.beanRest.PicsumImageRest
import retrofit2.Call
import retrofit2.http.GET

interface ApiPicsumInterface {

    @GET("/v2/list")
    fun getImage(): Call<Array<PicsumImageRest>>

}