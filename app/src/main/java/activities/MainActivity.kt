package activities

import API.ApiClient
import activities.fragments.*
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.AuthRequest
import utils.AuthResponse
import utils.prefs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {

            val username = username.text
            val password = password.text

            val request = AuthRequest(username.toString(), password.toString())

            ApiClient.getClient.doAuth(request).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    print("Login Fallito")
                }

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    if (!response.body()?.authToken.isNullOrEmpty()) {
                        prefs.rememberPassword = response.body()?.authToken.toString();
                    }
                }
            })
            val intent = Intent(applicationContext, HomeActivity::class.java)
            val bundle = Bundle()
            bundle.putString("chiave", "valore")
            startActivity(intent, bundle)
        }
    }
}