package com.lynx.instagrammo.API

import com.lynx.instagrammo.AuthRequest
import com.lynx.instagrammo.AuthResponse
import com.lynx.instagrammo.FollowerResponse
import com.lynx.instagrammo.PostResponse
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