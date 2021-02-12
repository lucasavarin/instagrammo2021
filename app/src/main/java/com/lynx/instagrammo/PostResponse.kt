package com.lynx.instagrammo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.lynx.instagrammo.bean.Post

data class PostResponse(

        @Expose
        @SerializedName("payload")
        val payload: List<Post>?
)
