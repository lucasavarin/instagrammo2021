package com.example.instagrammo.view.views.add

import androidx.fragment.app.Fragment
import com.example.instagrammo.beans.business.lorem.LoremBean

interface OnImageItemClickListener {
    fun onImageItemAddListener(image: LoremBean, tag: String)

    fun onImageItemAddPostListener(image: LoremBean)

    fun onImagePostsListener(fragment: Fragment)
}