package com.example.instagrammo.view.views.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.views.BaseActivity

class AddFragment : Fragment(){

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_add, container, false)

        return this.mView
    }




    companion object {
        var newInstance : AddFragment = AddFragment()
    }
}