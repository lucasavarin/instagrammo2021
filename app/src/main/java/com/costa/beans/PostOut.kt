package com.costa.beans

import java.util.*


data class PostOut(
    val profileId: String,
    val postId: String,
    val title: String,
    val picture: String,
    val uploadTime: String,
    val profile: ProfileOut
) {
    /*fun toPost(): Post {
        return Post(profileId, postId, title, picture, uploadTime, profile.toProfile())
    }*/
}
