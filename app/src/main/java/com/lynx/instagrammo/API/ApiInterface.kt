package com.lynx.instagrammo.API

import com.lynx.instagrammo.AuthRequest
import com.lynx.instagrammo.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest): Call<AuthResponse>
}