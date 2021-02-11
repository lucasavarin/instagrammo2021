package com.lynxspa.instagrammo.`interface`


import com.lynxspa.instagrammo.retrofit.AuthRequest
import com.lynxspa.instagrammo.retrofit.AuthResponse
import com.lynxspa.instagrammo.retrofit.FollowerResponse
import com.lynxspa.instagrammo.retrofit.ImageRest
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>
    fun getImage(@Query("Page")page: Int): Call<Array<ImageRest>>
    fun getFollower(@Path(value = "profileId")profileId : String): Call<FollowerResponse>
}
