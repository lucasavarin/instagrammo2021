package com.lynx.instagrammo.view.followerDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.Follower
import kotlinx.android.synthetic.main.fragment_follower_detail.*

class FollowerDetailFragment : Fragment() {

    private lateinit var follower: Follower

    companion object {
        var newInstance: FollowerDetailFragment = FollowerDetailFragment()
    }

    //onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follower_detail, container, false)
    }

    //onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detail_follower_name.text = follower.name
    }

    fun getFollow(item: Follower) {
        follower = item
    }
}