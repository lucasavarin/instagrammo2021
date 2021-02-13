package com.lynx.instagrammo.networking.API

import com.lynx.instagrammo.networking.AuthRequest
import com.lynx.instagrammo.networking.AuthResponse
import com.lynx.instagrammo.networking.FollowerResponse
import com.lynx.instagrammo.networking.PostResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>

    @GET("followers.php/{profileId}")
    fun getFollower(@Path("profileId") profileId :String ) : Call<FollowerResponse>

    @GET("posts.php")
    fun getPost() : Call<PostResponse>
}