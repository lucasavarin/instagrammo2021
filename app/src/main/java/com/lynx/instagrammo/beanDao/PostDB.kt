package com.lynx.instagrammo.beanDao

import com.lynx.instagrammo.bean.converter.interfaces.DaoModel

data class PostDB(
        val profileId: String,
        val postId: String,
        val title: String,
        val picture: String,
        val uploadTime: String,
) : DaoModel