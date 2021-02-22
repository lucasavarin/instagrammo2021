package com.lynxspa.instagrammo.customView

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.lynxspa.instagrammo.R

class NavigationHeader(context: Context): ConstraintLayout(context) {
    init {
        View.inflate(context, R.layout.navigation_header_view, this)
    }
}