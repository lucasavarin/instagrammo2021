package activities

import API.ApiClient
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import util.AuthRequest
import util.AuthResponse
import util.prefs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = AuthRequest("lsavarin", "lucasava")

        /*login.setOnClickListener {
            ApiClient.GetClient.doAuth(request).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    print("Login Fallito")
                }

                override fun onResponse(
                        call: Call<AuthResponse>,
                        response: Response<AuthResponse>
                ) {
                    if(!response.body()?.authToken.isNullOrEmpty()){
                        prefs.rememberPassword = response.body()?.authToken.toString();
                    }
                }
            })
        }*/
    }
}