package com.lynxspa.instagrammo.customView

import android.content.ComponentCallbacks
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.lynxspa.instagrammo.R
import kotlinx.android.synthetic.main.navigation_header_view.view.*

class NavigationHeader(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
    private val onBack: ArrayList<(() -> Unit)> = arrayListOf()
    init {
        inflate(context, R.layout.navigation_header_view, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.NavigationHeader)
        arrowBack.setOnClickListener { onBack.forEach{it.invoke()} }
        titleEdit.text = attributes.getString(R.styleable.NavigationHeader_title)
        attributes.recycle()

    }
    fun setOnBackListener (callback: () -> Unit){
        onBack.add(callback)
    }
}