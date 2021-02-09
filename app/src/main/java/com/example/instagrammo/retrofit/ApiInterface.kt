package com.example.instagrammo.retrofit

import com.example.instagrammo.bean.AuthRequest
import com.example.instagrammo.bean.AuthResponse
import com.example.instagrammo.bean.Post
import com.example.instagrammo.bean.Profile
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>

    @POST("posts.php/{profileId}")
    fun doGetProfile(
        @Body authRequestRest: AuthRequest,
        @Path("profileId") profileId : String
    ): Call<Profile>
}