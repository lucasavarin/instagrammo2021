package com.lynx.instagrammo.bean

import com.lynx.instagrammo.bean.converter.interfaces.BusinessModel

data class Post(
        val profileId: String,
        val postId: String,
        val title: String,
        val picture: String,
        val uploadTime: String,
        val profile: Profile?
) : BusinessModel
