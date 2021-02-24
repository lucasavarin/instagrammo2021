package com.lynxspa.instagrammo.singleton

import com.lynxspa.instagrammo.Instagrammo


    val prefs : Prefs by lazy {
        Instagrammo.prefs!!
    }
