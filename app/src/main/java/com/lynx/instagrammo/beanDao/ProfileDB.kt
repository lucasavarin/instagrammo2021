package com.lynx.instagrammo.beanDao

import com.lynx.instagrammo.bean.converter.interfaces.DaoModel

data class ProfileDB(
        val profileId: String,
        val name: String,
        val description: String,
        val picture: String,
        val followersNumber: String,
        val postsNumber: String
) :DaoModel
