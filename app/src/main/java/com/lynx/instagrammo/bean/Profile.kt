package com.lynx.instagrammo.bean

import com.lynx.instagrammo.bean.converter.interfaces.BusinessModel

data class Profile(
        val profileId: String,
        val name: String,
        val description: String,
        val picture: String,
        val followersNumber: String,
        val postsNumber: String
) : BusinessModel
