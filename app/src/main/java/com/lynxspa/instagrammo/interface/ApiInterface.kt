package com.lynxspa.instagrammo.`interface`


import com.lynxspa.instagrammo.retrofit.*
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>
    fun getImage(@Query("Page")page: Int): Call<Array<ImageRest>>
    @GET("followers.php/{profiloUtente}")
    fun getFollower(@Path("profiloUtente")profiloUtente : String): Call<FollowerResponse>
    @GET("posts.php/{profiloUtente}")
    fun getPost(@Path("profiloUtente")profiloUtente : String): Call<PostResponse>
}
