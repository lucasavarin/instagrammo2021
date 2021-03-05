package com.lynx.instagrammo.beanDao

import com.lynx.instagrammo.bean.converter.interfaces.DaoModel

data class FollowerDB(
        val id: String,
        val name: String,
        val description: String,
        val picture: String
) : DaoModel
