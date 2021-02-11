package `interface`


import retrofit.AuthRequest
import retrofit.AuthResponse
import retrofit.FollowerResponse
import retrofit.ImageRest
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>
    fun getImage(@Query("Page")page: Int): Call<Array<ImageRest>>
    fun getFollower(@Path(value = "profileId")profileId : String): Call<FollowerResponse>
}
