package com.example.instagrammo.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.instagrammo.R

/*
class HeaderCustomView : ConstraintLayout{
    constructor(context: Context, attrs: AttributeSet) : super(context,attrs){
        inflate(context, R.layout.custom_view_header, this)
    }
}
class CustomViewController @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


}
class CustomViewController : ConstraintLayout{
    constructor(context : Context,attrs : AttributeSet?) : super(context,attrs){
        View.inflate(context,R.layout.custom_view_controller,this)
    }
 */
class CustomViewController : ConstraintLayout {
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        View.inflate(context, R.layout.custom_view_controller, this)
    }
}



