package activities

import API.ApiClient
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagrammo.R
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import util.AuthRequest
import util.AuthResponse

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*fun authRequest = AuthRequest (
                username = editTextUsername.text.toString(),
                password = editTextPassword.text.toString()
                )
        ApiClient.getClient.doAuth(authRequest()).enqueue(object : Callback<AuthResponse>
        {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })
        */
    }
}