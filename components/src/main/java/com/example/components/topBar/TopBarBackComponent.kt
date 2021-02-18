package com.example.components.topBar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import com.example.components.R

class TopBarBackComponent (
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    init{
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.top_bar_back_component, this, true)
    }

}