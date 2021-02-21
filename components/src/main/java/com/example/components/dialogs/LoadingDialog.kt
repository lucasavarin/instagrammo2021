package com.example.components.dialogs

import android.app.Activity
import android.app.AlertDialog
import com.example.components.R

class LoadingDialog(

    val activity: Activity

) {
    private lateinit var alert : AlertDialog

    fun start() {
        val builder = AlertDialog.Builder(activity)

        val inflater = activity.layoutInflater

        builder.setView(inflater.inflate(R.layout.spinner_loader_dialog, null))

        builder.setCancelable(true)

        alert = builder.create()
        alert.show()
        alert.window?.setBackgroundDrawable(null)
    }

    fun dismiss() {
        alert.dismiss()
    }
}