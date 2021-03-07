package com.costa.beans.business

import com.costa.`interface`.BusinessModel

class Post(
    val profileId: String,
    val postId: String,
    val title: String,
    val picture: String,
    val uploadTime: String,
    val profile: Profile?
) : BusinessModel