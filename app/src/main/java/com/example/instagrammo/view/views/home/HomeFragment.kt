package com.example.instagrammo.view.views.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.followers.FollowerBean
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.utils.adapter.*
import com.example.instagrammo.utils.listener.OnFollowItemClickListener
import com.example.instagrammo.utils.listener.OnPostItemClickListener
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private var listenerPost: OnPostItemClickListener? = null

    private var listenerFollow: OnFollowItemClickListener? = null

    private lateinit var itemsPost: List<PostBean>

    private lateinit var itemsFollow: List<FollowerBean>

    private val viewModel = MainViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getData()

        setObservable()
    }

    private fun getData() {
        viewModel.setStateEvent(MainStateEvent.GetPostsEvent)

        viewModel.setStateEvent(MainStateEvent.GetFollowersEvent)
    }

    private fun setObservable() {

        viewModel.dataStatePost.observe(viewLifecycleOwner, Observer { dataStatePosts ->
            when (dataStatePosts) {
                is DataState.Error -> {
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> {
                    itemsPost = dataStatePosts.data
                    setAdapterPost()
                }
            }
        })

        viewModel.dataStateFollowers.observe(viewLifecycleOwner, Observer { dataStateFollowers ->
            when (dataStateFollowers) {
                is DataState.Error -> {
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> {
                    itemsFollow = dataStateFollowers.data
                    setAdapterFollower()
                }
            }
        })
/*
        ApiClient.GetClient.getPosts()
            .enqueue(object : Callback<PostResponse> {
                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                    if(response.isSuccessful &&  response.body()?.result == true) {
                        itemsPost = response.body()!!.payload!!.map { post ->
                            PostBean.convert(post)
                        }
                        setAdapterPost()
                    }
                }

            })

        ApiClient.GetClient.getFollowers(prefs.rememberIdProfile)
            .enqueue(object : Callback<FollowersResponse> {
                override fun onFailure(call: Call<FollowersResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(call: Call<FollowersResponse>, response: Response<FollowersResponse>) {
                    if(response.isSuccessful &&  response.body()?.result == true) {
                        itemsFollow = response.body()!!.payload!!.map { follower ->
                            FollowerBean.convert(follower)
                        }
                        setFollowAdapter()
                    }
                }

            })

 */
    }

    private fun setAdapterPost() {
        val recyclerView = view?.home_post_recycler
        if (recyclerView is RecyclerView ) {
            recyclerView.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = ItemPostRecyclerViewAdapter(this.context, itemsPost, listenerPost)
            }
        }
    }

    private fun setAdapterFollower() {
        val recyclerView = view?.home_follow_recycler
        if (recyclerView is RecyclerView) {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ItemFollowRecyclerViewAdapter(this.context, itemsFollow, listenerFollow)
            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPostItemClickListener) {
            listenerPost = context
        } else {
            throw RuntimeException("$context must implement OnPostItemClickListener")
        }
    }


    override fun onDetach() {
        super.onDetach()
        listenerPost = null
    }

    companion object {
        var newInstance: HomeFragment = HomeFragment()
    }

}