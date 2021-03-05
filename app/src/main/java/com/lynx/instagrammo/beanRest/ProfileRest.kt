package com.lynx.instagrammo.beanRest

import com.lynx.instagrammo.bean.converter.interfaces.RestModel

data class ProfileRest(
        val profileId: String,
        val name: String,
        val description: String,
        val picture: String,
        val followersNumber: String,
        val postsNumber: String
) : RestModel
