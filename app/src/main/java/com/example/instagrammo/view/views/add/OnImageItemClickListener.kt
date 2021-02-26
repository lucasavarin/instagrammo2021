package com.example.instagrammo.view.views.add

import com.example.instagrammo.beans.business.lorem.LoremBean

interface OnImageItemClickListener {
    fun onImageItemAddListener(image: LoremBean)

    fun onImageItemAddPostListener(image: LoremBean)
}