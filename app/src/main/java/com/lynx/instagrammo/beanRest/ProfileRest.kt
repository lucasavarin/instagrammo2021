package com.lynx.instagrammo.beanRest

data class ProfileRest(
        val profileId: String,
        val name: String,
        val description: String,
        val picture: String,
        val followersNumber: String,
        val postsNumber: String)
