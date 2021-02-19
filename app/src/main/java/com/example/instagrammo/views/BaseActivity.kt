package com.example.instagrammo.views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.instagrammo.views.profile.EditProfileFragment


abstract class BaseActivity() : AppCompatActivity() {

    protected lateinit var context: Context

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    protected fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    protected fun replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction { replace(frameId, fragment) }
    }

    protected fun removeFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction { remove(fragment) }
    }



}
