package API

import bean.EditProfileResponse
import bean.Post
import bean.PostResponse
import bean.ProfileResponse
import okhttp3.ResponseBody
import retrofit2.Call
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

    /*@PUT("profiles.php")
    fun putProfile(
            @Body profile: EditProfileRequest
    ): Call<EditProfileResponse>*/
}