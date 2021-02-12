package com.example.instagrammo.retrofit

import com.example.instagrammo.beans.auth.AuthRequest
import com.example.instagrammo.beans.auth.AuthResponse
import com.example.instagrammo.beans.followers.FollowersResponse
import com.example.instagrammo.beans.posts.PostResponse
import com.example.instagrammo.beans.posts.Profile
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>

    @GET("followers.php/{profileId}")
    fun getFollowers(
        @Path("profileId") profileId: String //= ApiClient.PROFILE_ID
    ) : Call<FollowersResponse>

    @GET("posts.php")
    fun getPosts(
    ) : Call<PostResponse>

    @GET
    fun getImage(@Url url: String): Call<ResponseBody>

    @GET("posts.php/{profileId}")
    fun getProfile(
        @Header("x-api-key") xapikey: String,
        @Path("profileId") profileId : String
    ): Call<Profile>
}