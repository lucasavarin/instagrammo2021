package com.example.instagrammo.environment.networking

import com.example.instagrammo.beans.rest.lorem.LoremRest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterfaceLorem {


    @GET("v2/list")
    fun getAllLoremPictures() : Call<List<LoremRest>>

    @GET("id/{id}/{width}/{height}")
    fun getLoremPicture(
        @Path("id") id : String,
        @Path("width") width : String,
        @Path("height") height : String
    ) : Call<LoremRest>
}