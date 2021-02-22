package com.example.components.topBar

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.components.R
import kotlinx.android.synthetic.main.top_bar_back_component.view.*

class TopBarBackComponent (context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private val onBackPressed : ArrayList<(() -> Unit)> = arrayListOf()

    init{

        inflate(context,R.layout.top_bar_back_component,this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomHeader)
        immagineFrecciaBack.setOnClickListener{
            onBackPressed.forEach{it.invoke()}
        }
        testoFragmentCorrente.text = attributes.getString(R.styleable.CustomHeader_title)

        attributes.recycle()

    }

    fun setOnPressedListener(callback : () -> Unit){
        onBackPressed.add (callback)
    }

}