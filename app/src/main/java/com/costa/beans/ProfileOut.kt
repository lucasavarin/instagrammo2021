package com.costa.beans

data class ProfileOut(
    var profileId: Int,
    var name: String,
    var description: String,
    var picture: String,
    var followersNumber: Int,
    var postsNumber: Int
){
    fun toProfile()=Profile(profileId, name, description, picture, followersNumber,postsNumber)
}