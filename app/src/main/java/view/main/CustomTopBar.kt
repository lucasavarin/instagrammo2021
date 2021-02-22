package view.main

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater
import android.widget.FrameLayout;
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.edit_top_bar.view.*

internal class CustomTopBar (context: Context, attrs:AttributeSet) : FrameLayout(context, attrs){

    private val backButtonPressed: ArrayList<(() -> Unit)> = arrayListOf()

    init{
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.edit_top_bar, this,true)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomTopBar)
        immagineFrecciaBack.setOnClickListener(){backButtonPressed.forEach{callback -> callback.invoke()}}
        testoFragmentCorrente.text = attributes.getString(R.styleable.CustomTopBar_title)
        attributes.recycle()
    }

    fun setOnBackEditProfileListener(callback: () -> Unit){
        backButtonPressed.add(callback)
    }

}



