package `interface`


import com.lynxspa.instagrammo.AuthRequest
import com.lynxspa.instagrammo.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiInterface {
    @POST("auth.php")
    fun doAuth(@Body authRequestRest: AuthRequest): Call<AuthResponse>
}
