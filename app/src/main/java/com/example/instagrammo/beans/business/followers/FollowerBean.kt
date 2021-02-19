package com.example.instagrammo.beans.business.followers

import com.example.instagrammo.beans.DataModel
import com.example.instagrammo.beans.DataConverter
import com.example.instagrammo.beans.rest.follower.FollowerRest

data class FollowerBean(
    val id: String,
    val name: String,
    val description: String,
    val picture: String
) : DataModel {

    companion object : DataConverter<FollowerBean, FollowerRest> {
        override fun convert(response: FollowerRest) : FollowerBean {
            return FollowerBean(
                response.id!!,
                response.name!!,
                response.description!!,
                response.picture!!
            )
        }
    }
}