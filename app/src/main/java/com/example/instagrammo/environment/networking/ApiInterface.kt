package com.example.instagrammo.environment.networking

import com.example.instagrammo.beans.rest.auth.AuthRequest
import com.example.instagrammo.beans.rest.auth.AuthResponse
import com.example.instagrammo.beans.rest.follower.FollowersResponse
import com.example.instagrammo.beans.rest.lorem.LoremRest
import com.example.instagrammo.beans.rest.post.AddPostRequest
import com.example.instagrammo.beans.rest.post.AddPostResponse
import com.example.instagrammo.beans.rest.post.NumberPosts
import com.example.instagrammo.beans.rest.post.PostsResponse
import com.example.instagrammo.beans.rest.profile.edit.EditProfileResponse
import com.example.instagrammo.beans.rest.profile.edit.EditProfileRequest
import com.example.instagrammo.beans.rest.profile.ProfileResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(
        @Body authRequestRest: AuthRequest
    ): Call<AuthResponse>

    @GET("followers.php/{profileId}")
    fun getFollowers(
        @Path("profileId") profileId: String
    ) : Call<FollowersResponse>

    @GET("posts.php")
    fun getPosts(
    ) : Call<PostsResponse>

    @GET
    fun getImage(@Url url: String): Call<ResponseBody>

    @GET("profiles.php/{profileId}")
    fun getProfile(
        @Path("profileId") profileId : String
    ): Call<ProfileResponse>

    @PUT("profiles.php")
    fun putProfile(
        @Body profile: EditProfileRequest
    ): Call<EditProfileResponse>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("posts.php/{profileId}")
    fun postAddPost(
        @Path("profileId") profileId :String,
        @Body post: AddPostRequest
    ) : Call<AddPostResponse>

    @GET("posts_number.php")
    fun getNumberPosts() : Call<NumberPosts>

}