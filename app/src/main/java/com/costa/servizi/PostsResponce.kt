package com.costa.servizi


import com.costa.beans.PostOut
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostsResponce(
        @Expose
        @SerializedName("payload")
        val payload: List<PostOut>?
) {

}