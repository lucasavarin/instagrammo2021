package com.example.instagrammo.view.views.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.utils.adapter.ItemPostRecyclerViewAdapter
import com.example.instagrammo.utils.listener.OnPostItemClickListener
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.grid_recycler_fragment.*

class GridRecyclerFragment: Fragment() {

    private lateinit var itemsPost: List<PostBean>

    private var listenerPost: OnPostItemClickListener? = null

    private val viewModel = ViewModelRecyclerView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.grid_recycler_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservable()
    }

    private fun setObservable() {

        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataStatePosts ->
            when (dataStatePosts) {
                is DataState.Error -> {
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> {
                    itemsPost = dataStatePosts.data
                    setAdapterGrid()
                }
            }
        })
    }

    private fun setAdapterGrid() {
        val recyclerView = recycler_post_grid_view
        if (recyclerView is RecyclerView) {
            recyclerView.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = ItemPostRecyclerViewAdapter(this.context, itemsPost, listenerPost, true )
            }
        }
    }



    companion object {
        val newInstance : GridRecyclerFragment = GridRecyclerFragment()
    }
}