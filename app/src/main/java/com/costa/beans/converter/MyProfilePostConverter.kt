package com.costa.beans.converter

import com.costa.`interface`.RestConverter
import com.costa.beans.business.MyProfilePost
import com.costa.beans.rest.MyProfilePostsOut

object MyProfilePostConverter : RestConverter<MyProfilePostsOut, MyProfilePost> {
    override fun restToBusiness(restModel: MyProfilePostsOut): MyProfilePost =
        MyProfilePost(restModel.postId, restModel.title, restModel.picture, restModel.uploadTime)

    override fun businessToRest(businessModel: MyProfilePost): MyProfilePostsOut =
        MyProfilePostsOut(businessModel.postId, businessModel.title, businessModel.picture, businessModel.uploadTime)

}