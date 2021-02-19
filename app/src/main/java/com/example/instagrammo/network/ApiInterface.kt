package com.example.instagrammo.network

import com.example.instagrammo.request.AuthRequest
import com.example.instagrammo.request.EditProfileRequest
import com.example.instagrammo.response.AuthResponse
import com.example.instagrammo.response.FollowerResponse
import com.example.instagrammo.response.PostResponse
import com.example.instagrammo.response.ProfileResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getFollowers(@Path("profiloUtente") profiloUtente: String): Call<FollowerResponse>

    @GET("posts.php")
    fun getPost(): Call<PostResponse>

    @GET("posts.php/{profileId}")
    fun getProfilePosts(@Path("profileId") profileId: String): Call<PostResponse>

    @GET("profiles.php/{profiloUtente}")
    fun getSingleProfile(@Path("profiloUtente") profiloUtente: String): Call<ProfileResponse>

    @Headers("Content-Type: application/json")
    @PUT("profiles.php/{profiloUtente}")
    fun saveChanges(@Path("profiloUtente")  profiloUtente: String , @Body editProfileReq : EditProfileRequest): Call<Boolean>
}