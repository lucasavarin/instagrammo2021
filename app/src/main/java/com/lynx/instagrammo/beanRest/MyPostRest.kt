package com.lynx.instagrammo.beanRest

import com.lynx.instagrammo.bean.converter.interfaces.RestModel

data class MyPostRest(
        val postId: String,
        val title: String,
        val picture: String,
        val uploadTime: String
) : RestModel
