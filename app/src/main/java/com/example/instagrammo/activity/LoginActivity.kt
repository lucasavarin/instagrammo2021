package com.example.instagrammo.activity

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.instagrammo.MainActivity
import com.example.instagrammo.R
import com.example.instagrammo.view.prefs
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.request.AuthRequest
import com.example.instagrammo.response.AuthResponse
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        checkbox_Remember.isChecked = prefs!!.rememberUser
        username.setText(prefs!!.username)

        login_button.setOnClickListener {
            if (check() == false) {
                alert()
            } else {
                login()
            }
        }
        progressBar = findViewById(R.id.progressbar)
    }


    fun alert() {
        val builder = AlertDialog.Builder(this@LoginActivity)
        builder.setTitle("ATTENZIONE")
        builder.setMessage("Controlla le credenziali da te inserite. Potrebbero essere sbagliate")
        builder.setPositiveButton("Riprova") { dialog, which ->
            Toast.makeText(
                applicationContext,
                "Riprova.",
                Toast.LENGTH_SHORT
            ).show()

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun check(): Boolean {
        if (password.text.toString().isEmpty() || username.text.toString().isEmpty()) {
            return false
        }
        return true
    }

    fun login() {
        val authRequest = AuthRequest(
            username = username.text.toString(),
            password = password.text.toString()

        )
        ApiClient.getClient.doAuth(authRequest).enqueue(object : Callback<AuthResponse> {

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                println("hai fallito-----------> " + t.message)

            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                prefs!!.rememberUser = checkbox_Remember.isChecked
                prefs!!.username = if (prefs!!.rememberUser) username.text.toString() else ""
                prefs!!.idProfilo = response.body()?.profileId.toString()
                prefs!!.token = response.body()?.authToken.toString()
                progressBar.visibility = View.VISIBLE
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }

        })
    }


}