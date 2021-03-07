package com.lynxspa.instagrammo.`interface`

import com.lynxspa.instagrammo.retrofit.FollowerResponse
import com.lynxspa.instagrammo.retrofit.PicsumResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicsumInterface {
    @GET("v2/list/")
    fun getPhoto(@Query("page") page:Int,
                 @Query("limit") limit:Int): Call<Array<PicsumResponse>>
}