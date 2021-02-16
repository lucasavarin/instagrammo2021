package com.example.instagrammo.beans.posts

import com.example.instagrammo.beans.DataModel
import com.example.instagrammo.beans.DataConverter
import com.example.instagrammo.beans.profile.ProfileBean

class PostBean (
    var profileId : String,
    var postId : String,
    var title : String,
    var picture : String,
    var uploadTime : String,
    var profile : ProfileBean
) : DataModel {

    companion object : DataConverter<PostBean, Post> {
        override fun convert(response: Post) : PostBean {
            return PostBean(
                response.profileId!!,
                response.postId!!,
                response.title!!,
                response.picture!!,
                response.uploadTime!!,
                ProfileBean.convert(response.profile)
                )
        }
    }
}