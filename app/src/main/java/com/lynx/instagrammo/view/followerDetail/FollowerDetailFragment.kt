package com.lynx.instagrammo.view.followerDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.Follower
import com.lynx.instagrammo.view.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_follower_detail.*

class FollowerDetailFragment : Fragment() {
private lateinit var follower: Follower

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_follower_detail, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detail_follower_name.text = follower.name
    }

    companion object {
    var newInstance: FollowerDetailFragment = FollowerDetailFragment()
    }

    fun getFollow(item: Follower){
        follower = item
    }
}