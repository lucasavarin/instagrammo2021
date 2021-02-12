package com.lynx.instagrammo

import com.lynx.instagrammo.utils.SharedPrefs

val prefs: SharedPrefs by lazy{
    ApplicationContext.prefs!!
}

