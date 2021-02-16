package com.costa.`interface`


import com.costa.servizi.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
            @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>

    @GET("followers.php/{profileId}")
    fun getFollowes(@Path(value = "profileId")profileId:String): Call<FollowersResponse>

    @GET("posts.php")
    fun getPost(): Call<PostsResponse>

    @GET("profiles.php/{profileId}")
    fun getProfile(@Path(value = "profileId")profileId:String): Call<ProfileResponse>

    @GET("posts.php/{profileId}")
    fun getMyPosts(@Path(value = "profileId")profileId:String): Call<MyPostsResponce>
}