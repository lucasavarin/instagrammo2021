package com.example.instagrammo.retrofit

import com.example.instagrammo.bean.AuthRequest
import com.example.instagrammo.bean.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>
}