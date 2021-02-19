package com.example.instagrammo.beans.business.profile

import com.example.instagrammo.beans.DataModel
import com.example.instagrammo.beans.DataConverter
import com.example.instagrammo.beans.rest.profile.Profile

data class ProfileBean (
    val profileId : String?,
    val name : String?,
    val description : String?,
    val picture : String?,
    val followersNumber : String?,
    val postsNumber : String?
) : DataModel {

    companion object : DataConverter<ProfileBean, Profile> {
        override fun convert(response: Profile) : ProfileBean {
            return ProfileBean(
                response.profileId!!,
                response.name!!,
                response.description!!,
                response.picture!!,
                response.followersNumber!!,
                response.postsNumber!!
            )
        }
    }
}