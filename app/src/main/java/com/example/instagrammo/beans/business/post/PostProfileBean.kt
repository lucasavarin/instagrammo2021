package com.example.instagrammo.beans.business.post

import com.example.instagrammo.beans.DataConverter
import com.example.instagrammo.beans.DataModel
import com.example.instagrammo.beans.business.profile.ProfileBean
import com.example.instagrammo.beans.rest.post.PostRest
import com.example.instagrammo.beans.rest.post.profilepost.PostsProfileRest

class PostProfileBean (
    var postId : String,
    var title : String,
    var picture : String,
    var uploadTime : String

) : DataModel {

    companion object : DataConverter<PostProfileBean, PostsProfileRest> {
        override fun convert(response: PostsProfileRest) : PostProfileBean {
            return PostProfileBean(
                response.postId!!,
                response.title!!,
                response.picture!!,
                response.picture!!
            )
        }
    }
}