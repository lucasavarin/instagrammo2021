package com.lynxspa.instagrammo.singleton

import com.lynxspa.instagrammo.InstagrammoApplication


    val prefs : Prefs by lazy {
        InstagrammoApplication.prefs!!
    }
