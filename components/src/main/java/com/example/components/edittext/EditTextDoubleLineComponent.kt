package com.example.components.edittext

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.components.R
import kotlinx.android.synthetic.main.edittext_dobleline_component.view.*

class EditTextDoubleLineComponent  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){


    init {
        LayoutInflater.from(context).inflate(R.layout.edittext_dobleline_component, this, true)
        orientation = VERTICAL

        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.edittext_doubleline_style, 0,0)

            //val title = resources.getText(typedArray.getResourceId(R.styleable.edittext_doubleline_component_custom_component_title, R.string.co))
            val title = typedArray.getString(R.styleable.edittext_doubleline_style_title_header).toString()

            //my_title.text = titl
            title_header.text = title
            typedArray.recycle()
        }
    }

}