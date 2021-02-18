package activities

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater
import android.widget.FrameLayout;
import com.example.instagrammo.R

internal class CustomTopBar (context: Context, attrs:AttributeSet) : FrameLayout(context, attrs){

    init{
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.edit_top_bar, this,true)
    }

}



