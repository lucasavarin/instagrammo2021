package com.example.instagrammo.beans.followers

import com.example.instagrammo.beans.DataModel
import com.example.instagrammo.beans.DataConverter

data class FollowerBean(
    val id: String,
    val name: String,
    val description: String,
    val picture: String
) : DataModel {

    companion object : DataConverter<FollowerBean, FollowerProfile> {
        override fun convert(response: FollowerProfile) : FollowerBean {
            return FollowerBean(
                response.id!!,
                response.name!!,
                response.description!!,
                response.picture!!
            )
        }
    }
}