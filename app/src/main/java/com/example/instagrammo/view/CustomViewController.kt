package com.example.instagrammo.view

import android.content.Context
import android.util.AttributeSet
import android.view.ActionMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.custom_view_controller.view.*


class CustomViewController (context : Context ,attrs : AttributeSet) : ConstraintLayout (context,attrs) {

     private val onBackPressed : ArrayList<(() -> Unit)> = arrayListOf()

    init {
        inflate(context,R.layout.custom_view_controller,this)

        val attributes = context.obtainStyledAttributes(attrs,R.styleable.CustomViewController)
        btn_back.setOnClickListener { onBackPressed.forEach{ it.invoke()} }
        modify_title.text = attributes.getString(R.styleable.CustomViewController_title)
        attributes.recycle()
    }

    fun setOnBackPressedListner(callback: () -> Unit){
        onBackPressed.add(callback)
    }


}



