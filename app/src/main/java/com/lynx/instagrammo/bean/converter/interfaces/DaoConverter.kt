package com.lynx.instagrammo.bean.converter.interfaces

interface DaoConverter<T : DaoModel, U : BusinessModel> {

    fun daoToBusiness(daoModel: T): U
    fun businessToDao(businessModel: U): T

    fun daoToBusiness(daoModels: List<T>): List<U> = daoModels.map(this::daoToBusiness)
    fun businessToDao(businessModels: List<U>): List<T> = businessModels.map(this::businessToDao)
}