package com.lynx.instagrammo

import com.lynx.instagrammo.Utils.SharedPrefs

val prefs: SharedPrefs by lazy{
    ApplicationContext.prefs!!
}