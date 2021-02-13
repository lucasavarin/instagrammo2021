package com.lynx.instagrammo

import com.lynx.instagrammo.persistence.SharedPrefs
import com.lynx.instagrammo.view.ApplicationContext

val prefs: SharedPrefs by lazy{
    ApplicationContext.prefs!!
}

