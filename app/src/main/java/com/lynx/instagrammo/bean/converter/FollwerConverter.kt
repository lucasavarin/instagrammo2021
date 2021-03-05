package com.lynx.instagrammo.bean.converter

import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.bean.converter.interfaces.DaoConverter
import com.lynx.instagrammo.bean.converter.interfaces.RestConverter
import com.lynx.instagrammo.beanDao.FollowerDB
import com.lynx.instagrammo.beanRest.FollowerRest

object FollwerConverter : RestConverter<FollowerRest, Follower>, DaoConverter<FollowerDB, Follower> {
    override fun restToBusiness(restModel: FollowerRest): Follower =
            Follower(restModel.id, restModel.name, restModel.description, restModel.picture)

    override fun businessToRest(businessModel: Follower): FollowerRest =
            FollowerRest(businessModel.id, businessModel.name, businessModel.description, businessModel.picture)

    override fun daoToBusiness(daoModel: FollowerDB): Follower =
            Follower(daoModel.id, daoModel.name, daoModel.description, daoModel.picture)

    override fun businessToDao(businessModel: Follower): FollowerDB =
            FollowerDB(businessModel.id, businessModel.name, businessModel.description, businessModel.picture)
}