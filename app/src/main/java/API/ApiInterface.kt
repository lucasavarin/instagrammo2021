package API

import bean.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
import utils.AuthRequest
import utils.AuthResponse
import utils.FollowerResponse

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getFollowers(@Path("profiloUtente") profiloUtente: String): Call<FollowerResponse>

    @GET("posts.php")
    fun getPosts() : Call<PostResponse>

    @GET
    fun getImage(@Url url: String): Call<ResponseBody>

    @GET("profiles.php/{profileId}")
    fun getProfile(
            @Path("profileId") profileId : String
    ): Call<ProfileResponse>

    @Headers("Content-Type: application/json")
    @PUT("profiles.php/{profileId}")
    fun putProfile(
        @Path("profileId") profileId : String,
        @Body profile: ProfileRequest
    ): Call<Boolean>
}





















