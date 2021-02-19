package com.lynx.instagrammo.view.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet


class CustomHeader : ConstraintLayout {
    constructor(context: Context?) : super(context!!) {}

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!,attrs) {}

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : this(context, attrs) {}
}