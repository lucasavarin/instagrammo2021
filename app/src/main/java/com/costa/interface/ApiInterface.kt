package com.costa.`interface`


import com.costa.servizi.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>

    @GET("followers.php/{profileId}")
    fun getFollowes(@Path(value = "profileId") profileId: String): Call<FollowersResponse>

    @GET("posts.php")
    fun getPost(): Call<PostsResponse>

    @GET("profiles.php/{profileId}")
    fun getProfile(@Path(value = "profileId") profileId: String): Call<ProfileResponse>

    @GET("posts.php/{profileId}")
    fun getMyPosts(@Path(value = "profileId") profileId: String): Call<MyPostsResponce>

    @Headers("Content-Type: application/json")
    @PUT("profiles.php/{profileId}")
    fun putEditProfile(
        @Path(value = "profileId") profileId: String,
        @Body profileEditRequest: ProfileEditRequest
    ): Call<BooleanRespomce>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("posts.php")
    fun doAddPost(@Body addPostRequestRest: AddPostRequestRest): Call<BooleanRespomce>

    @GET("posts_number.php")
    fun getPostsNumber():Call<NumberPostsResponse>
}