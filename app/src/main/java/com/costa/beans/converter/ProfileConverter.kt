package com.costa.beans.converter

import com.costa.`interface`.DaoConverter
import com.costa.`interface`.RestConverter
import com.costa.beans.business.Profile
import com.costa.beans.dao.ProfileDao
import com.costa.beans.rest.ProfileOut

object ProfileConverter : RestConverter<ProfileOut, Profile>, DaoConverter<ProfileDao,Profile>{
    override fun restToBusiness(restModel: ProfileOut): Profile =
        Profile(restModel.profileId, restModel.name, restModel.description, restModel.picture, restModel.followersNumber, restModel.postsNumber)

    override fun businessToRest(businessModel: Profile): ProfileOut =
        ProfileOut(businessModel.profileId, businessModel.name, businessModel.description, businessModel.picture, businessModel.followersNumber, businessModel.postsNumber)

    override fun daoToBusiness(daoModel: ProfileDao): Profile =
       Profile(daoModel.profileId,daoModel.name,daoModel.description,daoModel.picture,daoModel.followersNumber,daoModel.postsNumber)
    override fun businessToDao(businessModel: Profile): ProfileDao =
        ProfileDao(businessModel.profileId, businessModel.name, businessModel.description, businessModel.picture, businessModel.followersNumber, businessModel.postsNumber)
}