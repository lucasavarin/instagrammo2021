package com.costa.beans.dao

import com.costa.`interface`.DaoModel

class ProfileDao(
    var profileId: Int,
    var name: String,
    var description: String,
    var picture: String,
    var followersNumber: Int,
    var postsNumber: Int
): DaoModel