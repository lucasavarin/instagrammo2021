package com.costa.components

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.costa.instagrammo.R
import kotlinx.android.synthetic.main.custom_navigation_header.view.*

class CastomHeader(context:Context,attrs:AttributeSet):ConstraintLayout(context,attrs) {

    private val onBackPresed:ArrayList<(() ->Unit)> = arrayListOf()

    init {

        inflate(context, R.layout.custom_navigation_header,this)

        val attributes= context.obtainStyledAttributes(attrs,R.styleable.CastomHeader)
        bt_back_buttom.setOnClickListener { onBackPresed.forEach{it.invoke()} }
        tv_nome_pagina.text= attributes.getString(R.styleable.CastomHeader_title)
        attributes.recycle()
    }

    fun setOnBackPressedListener(callback: () -> Unit){
        onBackPresed.add(callback)
    }
}