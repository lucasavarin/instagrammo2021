package com.lynx.instagrammo.bean.converter

import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.beanDao.PostDB
import com.lynx.instagrammo.bean.converter.interfaces.DaoConverter
import com.lynx.instagrammo.bean.converter.interfaces.RestConverter
import com.lynx.instagrammo.beanRest.PostRest

object PostConverter : RestConverter<PostRest, Post>, DaoConverter<PostDB, Post> {

    override fun restToBusiness(restModel: PostRest): Post =
            Post(restModel.profileId, restModel.postId, restModel.title, restModel.picture, restModel.uploadTime, ProfileConverter.restToBusiness(restModel.profile!!))

    override fun businessToRest(businessModel: Post): PostRest =
            PostRest(businessModel.profileId, businessModel.postId, businessModel.title, businessModel.picture, businessModel.uploadTime, null)

    override fun daoToBusiness(daoModel: PostDB): Post =
            Post(daoModel.profileId, daoModel.postId, daoModel.title, daoModel.picture, daoModel.uploadTime, null)

    override fun businessToDao(businessModel: Post): PostDB =
            PostDB(businessModel.profileId, businessModel.postId, businessModel.title, businessModel.picture, businessModel.uploadTime)


}