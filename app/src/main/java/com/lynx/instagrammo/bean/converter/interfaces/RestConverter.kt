package com.lynx.instagrammo.bean.converter.interfaces

interface RestConverter<T : RestModel, U : BusinessModel> {

    fun restToBusiness(restModel: T): U
    fun businessToRest(businessModel: U): T

    fun restToBusiness(restModels: List<T>): List<U> = restModels.map(this::restToBusiness)
    fun businessToRest(businessModels: List<U>): List<T> = businessModels.map(this::businessToRest)
}