package com.lynx.instagrammo.networking.API

import com.lynx.instagrammo.networking.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>

    @GET("followers.php/{profileId}")
    fun getFollower(@Path("profileId") profileId :String ) : Call<FollowerResponse>

    @GET("posts.php")
    fun getPost() : Call<PostResponse>

    @GET("profiles.php/{profileId}")
    fun getSingleProfile(@Path("profileId") profileId :String) : Call<ProfileResponse>

    @GET("posts.php/{profileId}")
    fun getMyPosts(@Path("profileId") profileId :String) : Call<MyPostsResponse>
}