package com.example.instagrammo.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>
}