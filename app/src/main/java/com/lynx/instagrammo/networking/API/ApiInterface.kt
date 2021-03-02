package com.lynx.instagrammo.networking.API

import com.lynx.instagrammo.bean.Profile
import com.lynx.instagrammo.networking.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>

    @GET("followers.php/{profileId}")
    fun getFollower(@Path("profileId") profileId :String ) : Call<FollowerResponse>

    @GET("posts.php")
    fun getPost() : Call<PostResponse>

    @GET("profiles.php/{profileId}")
    fun getSingleProfile(@Path("profileId") profileId :String) : Call<ProfileResponse>

    @GET("posts.php/{profileId}")
    fun getMyPosts(@Path("profileId") profileId :String) : Call<MyPostsResponse>

    @Headers("Content-Type: application/json")
    @PUT("profiles.php/{profileId}")
    fun putEditProfile(@Path("profileId") profileId :String,
                       @Body profile: ProfileRequest) : Call<Boolean>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("posts.php/{profileId}")
    fun addPost(@Path("profileId") profileId :String,
                @Body post: AddPostRequest) : Call<Boolean>

    @GET("posts_number.php/")
    fun getNumberPost() : Call<NumberPostsResponse>

    @DELETE("posts.php/postsId")
    fun deletePost(@Path("postsId")postsId :String) : Call<Boolean>

}