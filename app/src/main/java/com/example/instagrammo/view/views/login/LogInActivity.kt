package com.example.instagrammo.view.views.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.instagrammo.R
import com.example.instagrammo.prefs
import com.example.instagrammo.beans.rest.auth.AuthRequest
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.utils.ElementViewConverter.toEditable
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.example.instagrammo.view.BaseHomeActivity
import com.example.instagrammo.views.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LogInActivity : BaseActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loadUsername()

        setListener()

    }

    private fun loadUsername() {
        if (prefs.rememberMe) {
            username_view.text = prefs.rememberUsername.toEditable()
            rememberme_switch.isChecked = true
        }
    }

    private fun setListener() {
        btnAccess.setOnClickListener {

            val request = inputControl()

            viewModel.setStateEvent(MainStateEvent.PostAuthEvent(request))
            //val request = AuthRequest(username, password)

            setObservable()

    /*
                ApiClient.GetClient.doAuth(request).enqueue(object : Callback<AuthResponse> {

                    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                        print("Login Fallito")
                    }

                    override fun onResponse(
                        call: Call<AuthResponse>,
                        response: Response<AuthResponse>
                    ) {
                        if (response.isSuccessful && response.body()?.result == true) {
                            prefs.rememberToken = response.body()?.authToken.toString()
                            prefs.rememberIdProfile = response.body()?.profileId.toString()
                            val intentLogin = Intent(applicationContext, BaseHomeActivity::class.java)
                            startActivity(intentLogin)
                            finish()
                        } else {
                            Toast.makeText(this@LogInActivity, R.string.loginFallito, Toast.LENGTH_SHORT).show()
                        }
                    }

                })
    */

        }
    }

    private fun inputControl(): AuthRequest {
        val username: String = username_view.text.toString()
        val password: String = password_view.text.toString()

        if (rememberme_switch.isChecked) {
            prefs.rememberMe = true
            prefs.rememberUsername = username
        } else {
            prefs.rememberMe = false
            prefs.rememberUsername = ""
        }

        return AuthRequest("jsamson", "mson")
    }

    private fun setObservable() {
        viewModel.dataStateAuth.observe(this, Observer { dataStateAuth ->
            when (dataStateAuth) {
                is DataState.Error -> { }
                is DataState.Loading -> { }
                is DataState.Success -> { goToHome() }
            }
        })
    }

    private fun goToHome() {
        val intentLogin = Intent(applicationContext, BaseHomeActivity::class.java)
        startActivity(intentLogin)
        finish()
    }
}