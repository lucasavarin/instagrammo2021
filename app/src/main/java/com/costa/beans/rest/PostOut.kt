package com.costa.beans.rest

import com.costa.`interface`.RestModel
import com.costa.beans.rest.ProfileOut


data class PostOut(
    val profileId: String,
    val postId: String,
    val title: String,
    val picture: String,
    val uploadTime: String,
    val profile: ProfileOut
): RestModel