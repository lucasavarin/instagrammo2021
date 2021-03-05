package com.lynx.instagrammo.bean

import com.lynx.instagrammo.bean.converter.interfaces.BusinessModel

data class Follower(
        val id: String,
        val name: String,
        val description: String,
        val picture: String
) : BusinessModel

