package com.costa.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.costa.instagrammo.R

/**
 * A simple [Fragment] subclass.
 * Use the [LikeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LikeFragment : Fragment() {
    companion object{
        val instance:LikeFragment= LikeFragment()

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_like, container, false)

    }
}