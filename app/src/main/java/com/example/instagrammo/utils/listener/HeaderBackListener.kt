package com.example.instagrammo.utils.listener

import androidx.fragment.app.Fragment

interface HeaderBackListener {
    fun removeFragmentListener (fragment: Fragment)
    fun addFragment ()
}