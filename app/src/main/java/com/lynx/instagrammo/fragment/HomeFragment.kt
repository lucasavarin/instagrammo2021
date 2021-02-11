package com.lynx.instagrammo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lynx.instagrammo.R
import com.lynx.instagrammo.loadPosts
import com.lynx.instagrammo.recycleView.PostListAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*colleghiamo la nostra recycleview al homefragment */
        val linearLayoutManager = LinearLayoutManager(context)
        V_FollowerListLayout.layoutManager = linearLayoutManager

        V_FollowerListLayout.adapter = PostListAdapter(loadPosts())


    }

}
