package com.lynx.instagrammo.networking

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lynx.instagrammo.bean.MyPost
import com.lynx.instagrammo.beanRest.MyPostRest

data class MyPostsResponse(
        @Expose
        @SerializedName("payload")
        val payload: List<MyPostRest>?
)
