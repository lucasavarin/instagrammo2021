package com.lynx.instagrammo.beanRest

import com.lynx.instagrammo.bean.Profile

data class PostRest(
        val profileId: String,
        val postId: String,
        val title: String,
        val picture: String,
        val uploadTime: String,
        val profile: Profile
)
