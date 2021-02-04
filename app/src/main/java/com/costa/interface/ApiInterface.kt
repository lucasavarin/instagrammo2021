package com.costa.`interface`


import com.costa.servizi.AuthRequest
import com.costa.servizi.AuthResponse
import com.costa.servizi.FollowersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
            @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>

    @POST("followers.php/{profiloUtente}")
    fun doFollowes(
    ): Call<FollowersResponse>


}