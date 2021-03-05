package com.lynx.instagrammo.bean.converter

import com.lynx.instagrammo.bean.Profile
import com.lynx.instagrammo.bean.converter.interfaces.RestConverter
import com.lynx.instagrammo.beanRest.ProfileRest

object ProfileConverter : RestConverter<ProfileRest, Profile> {
    override fun restToBusiness(restModel: ProfileRest): Profile =
            Profile(restModel.profileId, restModel.name, restModel.description, restModel.picture, restModel.followersNumber, restModel.postsNumber)

    override fun businessToRest(businessModel: Profile): ProfileRest =
            ProfileRest(businessModel.profileId, businessModel.name, businessModel.description, businessModel.picture, businessModel.followersNumber, businessModel.postsNumber)
}