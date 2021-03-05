package com.lynx.instagrammo.bean.converter

import com.lynx.instagrammo.bean.MyPost
import com.lynx.instagrammo.bean.converter.interfaces.RestConverter
import com.lynx.instagrammo.beanRest.MyPostRest

object MyPostConverter : RestConverter<MyPostRest, MyPost> {
    override fun restToBusiness(restModel: MyPostRest): MyPost =
            MyPost(restModel.postId, restModel.title, restModel.picture, restModel.uploadTime)

    override fun businessToRest(businessModel: MyPost): MyPostRest =
            MyPostRest(businessModel.postId, businessModel.title, businessModel.picture, businessModel.uploadTime)

}