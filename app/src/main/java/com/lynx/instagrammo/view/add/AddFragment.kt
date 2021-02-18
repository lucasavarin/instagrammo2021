package com.lynx.instagrammo.view.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lynx.instagrammo.R
import com.lynx.instagrammo.view.edit.EditFragment

class AddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    companion object{
        val newInstance: AddFragment = AddFragment()
    }
}