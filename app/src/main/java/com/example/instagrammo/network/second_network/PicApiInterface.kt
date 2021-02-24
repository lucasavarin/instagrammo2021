package com.example.instagrammo.network.second_network

import com.example.instagrammo.response.PictureResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicApiInterface {

    @GET ("/v2/list")
    fun getPicture(@Query("page") page:Int, @Query("limit") limit:Int): Call<List<PictureResponse>>
}