package com.costa.beans

data class Profile(
       var profileId: Int,
       var name: String,
       var description: String,
       var picture: String,
       var followersNumber: Int,
       var postsNumber: Int
)