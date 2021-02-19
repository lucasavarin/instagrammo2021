package com.example.instagrammo.view.views.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R

class FollowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    companion object {
        var newInstance : FollowFragment = FollowFragment()
    }
}