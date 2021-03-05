package com.lynx.instagrammo.bean

import com.lynx.instagrammo.bean.converter.interfaces.BusinessModel

data class MyPost(
        val postId: String,
        val title: String,
        val picture: String,
        val uploadTime: String
) : BusinessModel
