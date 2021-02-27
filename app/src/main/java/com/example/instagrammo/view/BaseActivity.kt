package com.example.instagrammo.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.nfc.Tag
import com.example.components.dialogs.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.components.dialogs.LoadingDialog


abstract class BaseActivity() : AppCompatActivity() {

    val loading = LoadingDialog(this)
    lateinit var alertDialog: AlertDialog

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

    protected fun addToBackStackFragment(tag: String) {
        supportFragmentManager.inTransaction { addToBackStack(tag) }
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

    protected fun createAlertDialog(context: Context, title: String,
                                    messege: String, textButton: String, icon: Drawable) {
        alertDialog = AlertDialog(context, title, messege, textButton, icon)
        alertDialog.start()
    }

    protected fun alertDismiss() {
        alertDialog.dismiss()
    }


}
