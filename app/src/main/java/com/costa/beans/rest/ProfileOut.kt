package com.costa.beans.rest

import com.costa.`interface`.RestModel

data class ProfileOut(
    var profileId: Int,
    var name: String,
    var description: String,
    var picture: String,
    var followersNumber: Int,
    var postsNumber: Int
): RestModel