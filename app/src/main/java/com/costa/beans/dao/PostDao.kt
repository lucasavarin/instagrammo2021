package com.costa.beans.dao

import com.costa.`interface`.DaoModel

class PostDao (
    val profileId: String,
    val postId: String,
    val title: String,
    val picture: String,
    val uploadTime: String
): DaoModel