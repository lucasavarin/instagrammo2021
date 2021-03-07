package com.costa.beans.converter

import com.costa.`interface`.RestConverter
import com.costa.`interface`.DaoConverter
import com.costa.beans.business.Follower
import com.costa.beans.dao.FollowerDao
import com.costa.beans.rest.FollowerOut

object FollowerConverter : RestConverter<FollowerOut, Follower>, DaoConverter<FollowerDao, Follower> {
    override fun restToBusiness(restModel: FollowerOut): Follower =
        Follower(restModel.id, restModel.name, restModel.description, restModel.picture)

    override fun businessToRest(businessModel: Follower): FollowerOut =
        FollowerOut(businessModel.id, businessModel.name, businessModel.description, businessModel.picture)

    override fun daoToBusiness(daoModel: FollowerDao): Follower =
        Follower((daoModel.id as Int), daoModel.name, daoModel.description, daoModel.picture)

    override fun businessToDao(businessModel: Follower): FollowerDao =
        FollowerDao(businessModel.id.toString(), businessModel.name, businessModel.description, businessModel.picture)
}