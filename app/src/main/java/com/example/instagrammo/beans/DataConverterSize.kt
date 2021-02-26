package com.example.instagrammo.beans

interface DataConverterSize <R: DataModel, T: ResponseDataModel> {
    fun convert(response: T, size : String) : R
}