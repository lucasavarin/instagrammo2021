package com.example.components.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import com.example.components.R

class AlertDialog(
    private val context: Context,
    private val title: String,
    private val messege: String,
    private val textButton: String,
    private val icon: Drawable


) {
    private lateinit var alert : AlertDialog

    fun start() {
        val builder = AlertDialog.Builder(context)

        builder.setTitle(title)
        builder.setMessage(messege)
        builder.setIcon(icon)

        builder.setPositiveButton(textButton) { dialog, _ -> dialog.dismiss() }

        alert = builder.create()
        alert.show()

    }

    fun dismiss() {
        alert.dismiss()
    }
}