package com.example.instagrammo.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.instagrammo.R
import com.example.instagrammo.view.login.LogInActivity
import com.google.android.material.navigation.NavigationView


abstract class BaseActivity() : AppCompatActivity() {

    private val navigationView: NavigationView
        get() {
            TODO()
        }

    protected lateinit var context: Context

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        val window: Window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkblue)
    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    protected fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { addFragment(fragment, frameId) }
    }

    protected fun replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replaceFragment(fragment, frameId) }
    }

}
