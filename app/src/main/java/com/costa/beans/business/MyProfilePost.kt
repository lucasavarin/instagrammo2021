package com.costa.beans.business

import com.costa.`interface`.BusinessModel

class MyProfilePost (
    val postId: String,
    val title: String,
    val picture: String,
    val uploadTime: String
): BusinessModel