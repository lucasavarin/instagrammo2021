package com.example.instagrammo.beans.business.post

import com.example.instagrammo.beans.DataModel
import com.example.instagrammo.beans.DataConverter
import com.example.instagrammo.beans.business.profile.ProfileBean
import com.example.instagrammo.beans.rest.post.PostRest

class PostBean (
    var profileId : String,
    var postId : String,
    var title : String,
    var picture : String,
    var uploadTime : String,
    var profile : ProfileBean
) : DataModel {

    companion object : DataConverter<PostBean, PostRest> {
        override fun convert(response: PostRest) : PostBean {
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