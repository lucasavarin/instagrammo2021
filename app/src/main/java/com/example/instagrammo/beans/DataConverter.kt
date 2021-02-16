package com.example.instagrammo.beans

interface DataConverter<R: DataModel, T: ResponseDataModel> {
    fun convert(response: T) : R
}