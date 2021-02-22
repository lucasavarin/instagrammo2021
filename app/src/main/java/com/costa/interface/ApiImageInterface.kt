package com.costa.`interface`

import com.costa.beans.PicSumImageOut
import com.costa.servizi.FollowersResponse
import com.costa.servizi.ListPicSumResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiImageInterface {
    @GET("v2/list/")
    fun getPictures(): Call<Array<PicSumImageOut>>
}