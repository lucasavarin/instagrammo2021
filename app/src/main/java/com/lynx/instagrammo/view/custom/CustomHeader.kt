package com.lynx.instagrammo.view.custom

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.lynx.instagrammo.R
import kotlinx.android.synthetic.main.custom_navigation_header.view.*


class CustomHeader(context: Context, attrs: AttributeSet?) : ConstraintLayout (context,attrs){

    private val onBackPressed :ArrayList<(() -> Unit)> = arrayListOf()

    init {
        inflate(context, R.layout.custom_navigation_header, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomHeader)
        edit_back_button.setOnClickListener{onBackPressed.forEach{it.invoke()}}
        edit_text_header.text = attributes.getString(R.styleable.CustomHeader_title)
        attributes.recycle()
    }

    fun setOnBackClickListener(callback: () -> Unit){
        onBackPressed.add{callback}

    }
}