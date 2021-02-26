package com.example.instagrammo.view.views.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
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
        btnAccess.isEnabled = false
        setListener()
        loadUsername()

    }

    private fun loadUsername() {
        if (prefs.rememberMe) {
            username_view.text = prefs.rememberUsername.toEditable()
            rememberme_switch.isChecked = true
        }
    }

    private fun setListener() {
        var userAvailable = false
        var passwordAvailable = false

        btnAccess.setOnClickListener {
            val request = inputControl()
            viewModel.setStateEvent(MainStateEvent.PostAuthEvent(request))

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

        username_view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(!s.isNullOrBlank()) {
                    userAvailable = true
                    btnAccess.isEnabled = passwordAvailable
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                btnAccess.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        password_view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(!s.isNullOrBlank()) {
                    passwordAvailable = true
                    btnAccess.isEnabled = userAvailable
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                btnAccess.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

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

        return AuthRequest(username, password)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setObservable() {
        viewModel.dataStateAuth.observe(this, Observer { dataStateAuth ->
            when (dataStateAuth) {
                is DataState.Error -> { createAlertDialog(this,  getString(R.string.login),
                    getString(R.string.login_fallito), getString(R.string.chiamata_fallita), getDrawable(R.drawable.alert)!!) }
                is DataState.Loading -> { loadingShow() }
                is DataState.Success -> {
                    loadingDismiss()
                    if (dataStateAuth.data)
                        goToHome()
                    else {
                        createAlertDialog(
                            this@LogInActivity,
                            getString(R.string.login),
                            getString(R.string.login_fallito),
                            getString(R.string.ok),
                            getDrawable(R.drawable.alert)!!
                        )

                    }
                }
            }
        })
    }

    private fun goToHome() {
        val intentLogin = Intent(applicationContext, BaseHomeActivity::class.java)
        startActivity(intentLogin)
        finish()
    }

}