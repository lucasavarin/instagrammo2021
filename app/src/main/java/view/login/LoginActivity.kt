package view.login

import utils.api.ApiClient
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import bean.rest.AuthRequest
import bean.rest.AuthResponse
import utils.extensions.prefs
import view.main.HomeActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(prefs.rememberUser){
            et_username.text = prefs.username.toEditable()
        }

        btn_login.setOnClickListener {

            val username = et_username.text
            val password = et_password.text

            val request = AuthRequest(username.toString(), password.toString())

            /*if(remind_me == true){
                et_username.text = prefs.username.toEditable()
            }*/

            ApiClient.getClient.doAuth(request).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    print("Login Fallito ${t.message}")
                }

                override fun onResponse(
                        call: Call<AuthResponse>,
                        response: Response<AuthResponse>
                ) {
                    if (!response.body()?.authToken.isNullOrEmpty()) {
                        prefs.rememberUser = remind_me.isChecked
                        prefs.username = if(remind_me.isChecked) et_username.text.toString() else ""
                        prefs.profileId = response.body()?.profileId.toString()
                        prefs.authToken = response.body()?.authToken.toString()

                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        val bundle = Bundle()
                        bundle.putString("chiave", "valore")
                        startActivity(intent, bundle)
                    }
                }
            })

        }
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}