package API

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import util.AuthRequest
import util.AuthResponse

interface ApiInterface {
    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>
}