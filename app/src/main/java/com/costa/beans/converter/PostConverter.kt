package com.costa.beans.converter

import com.costa.`interface`.DaoConverter
import com.costa.`interface`.RestConverter
import com.costa.beans.business.Post
import com.costa.beans.dao.PostDao
import com.costa.beans.rest.PostOut

object PostConverter : RestConverter<PostOut, Post>, DaoConverter<PostDao, Post> {

    override fun restToBusiness(restModel: PostOut): Post =
        Post(restModel.profileId, restModel.postId, restModel.title, restModel.picture, restModel.uploadTime, ProfileConverter.restToBusiness(restModel.profile!!))

    override fun businessToRest(businessModel: Post): PostOut =
        PostOut(businessModel.profileId, businessModel.postId, businessModel.title, businessModel.picture, businessModel.uploadTime, ProfileConverter.businessToRest(businessModel.profile!!))

    override fun daoToBusiness(daoModel: PostDao): Post =
        Post(daoModel.profileId, daoModel.postId, daoModel.title, daoModel.picture, daoModel.uploadTime,null)//TODO: chiamata lato db Per passare le info Tramite IdProfile

    override fun businessToDao(businessModel: Post): PostDao =
        PostDao(businessModel.profileId, businessModel.postId, businessModel.title, businessModel.picture, businessModel.uploadTime)

}