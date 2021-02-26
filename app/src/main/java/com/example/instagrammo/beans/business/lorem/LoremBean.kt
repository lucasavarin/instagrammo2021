package com.example.instagrammo.beans.business.lorem

import com.example.instagrammo.beans.DataConverter
import com.example.instagrammo.beans.DataConverterSize
import com.example.instagrammo.beans.DataModel
import com.example.instagrammo.beans.rest.lorem.LoremRest

data class LoremBean(
    val id: String,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val download_url: String
) : DataModel {

    companion object : DataConverter<LoremBean, LoremRest> {
        override fun convert(response: LoremRest) : LoremBean {
            //val dataModified =  response.download_url?.split("/")!!.mapIndexed { index, s -> if (index == 5 || index == 6 ) size else s }.joinToString("/")
            return LoremBean(
                response.id!!,
                response.author!!,
                response.width!!,
                response.height!!,
                response.url!!,
                response.download_url!!
            )
        }


    }
}