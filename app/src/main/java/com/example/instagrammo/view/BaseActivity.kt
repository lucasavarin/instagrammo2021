package com.example.instagrammo.views

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.components.dialogs.LoadingDialog


abstract class BaseActivity() : AppCompatActivity() {

    val loading= LoadingDialog(this)

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

    protected fun loadingShow() {
        loading.start()
    }

    protected fun loadingDismiss() {
        loading.dismiss()
    }

}
