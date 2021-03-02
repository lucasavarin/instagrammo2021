package com.costa.beans.business

import java.util.*

class Post(
    val profileId: String,
    val postId: String,
    val title: String,
    val picture: String,
    val uploadTime: Date,
    val profile: Profile
) {
}