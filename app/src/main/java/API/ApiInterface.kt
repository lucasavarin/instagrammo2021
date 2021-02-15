package API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import utils.AuthRequest
import utils.AuthResponse
import utils.FollowerResponse

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>

    @GET("followers.php/{profiloUtente}")
    fun getFollowers(@Path("profiloUtente") profiloUtente: String): Call<FollowerResponse>
}