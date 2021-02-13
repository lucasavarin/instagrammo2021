package com.example.instagrammo.utils

import android.text.Editable

object ElementViewConverter {

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}