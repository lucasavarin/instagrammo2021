package com.costa.components

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.costa.instagrammo.R
import kotlinx.android.synthetic.main.custom_navigation_header.view.*

class CastomHeader:ConstraintLayout {

    constructor(context:Context):super(context){

    }
    constructor(context:Context,attrs:AttributeSet):super(context,attrs){
        inflate(context, R.layout.custom_navigation_header,this)
      //  val attributes= context.obtainStyledAttributes(attrs,R.styleable.//TODO cosa ci va messo?)

     // bt_back_buttom.setOnClickListener { onBackPresed.forEach{it.invoke()} }
       // tv_nome_pagina.text= attributes.getString(R.styleable.//TODO cosa ci va messo?)
    }
    constructor(context:Context,attrs:AttributeSet,defStyleAttr:Int):super(context,attrs,defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}