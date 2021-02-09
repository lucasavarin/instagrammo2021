package com.example.instagrammo.network

import com.example.instagrammo.request.AuthRequest
import com.example.instagrammo.response.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
        @Body authRequestRest : AuthRequest
    ): Call<AuthResponse>
}