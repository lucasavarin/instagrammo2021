package com.example.instagrammo.utils.listener

import com.example.instagrammo.beans.business.lorem.LoremBean

interface OnImageItemClickListener {
    fun onImageItemAddListener(image: LoremBean)

    fun onImageItemAddPostListener(image: LoremBean)
}