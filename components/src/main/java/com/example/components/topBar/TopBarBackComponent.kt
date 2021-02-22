package com.example.components.topBar

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.components.R
import kotlinx.android.synthetic.main.top_bar_back_component.view.*

class TopBarBackComponent (context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private val onBackPressed : ArrayList<(() -> Unit)> = arrayListOf()

    init{
    /*
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.top_bar_back_component, this, true)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomHeader)

        immagineFrecciaBack.setOnClickListener(){onBackPressed.forEach{ callback -> callback.invoke() }}
        testoFragmentCorrente.text = attributes.getString(R.styleable.CustomHeader_title)

        attributes.recycle()
        */

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