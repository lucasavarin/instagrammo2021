package com.lynx.instagrammo.beanRest

import com.lynx.instagrammo.bean.converter.interfaces.RestModel

data class FollowerRest(
        val id: String,
        val name: String,
        val description: String,
        val picture: String
) : RestModel
