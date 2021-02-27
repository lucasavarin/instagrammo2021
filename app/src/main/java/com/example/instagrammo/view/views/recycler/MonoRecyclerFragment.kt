package com.example.instagrammo.view.views.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.utils.adapter.ItemGridRecyclerViewAdapter
import com.example.instagrammo.utils.listener.OnPostItemClickListener
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.example.instagrammo.view.views.profile.ButtonEditProfileListener
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.grid_recycler_fragment.*
import kotlinx.android.synthetic.main.mono_recycler_fragment.*
import kotlinx.android.synthetic.main.mono_recycler_fragment.view.*

class MonoRecyclerFragment : Fragment(){

    private lateinit var itemsPost: List<PostBean>

    private var listenerPost: OnPostItemClickListener? = null

    private val viewModel = ViewModelRecyclerView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mono_recycler_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservable()
    }


    private fun setAdapterMono() {
        val recyclerView = recycler_post_mono_view
        if (recyclerView is RecyclerView) {
            recyclerView.apply {
                layoutManager = GridLayoutManager(context, 4)
                adapter = ItemGridRecyclerViewAdapter(this.context, itemsPost, listenerPost )
            }
        }
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
                    setAdapterMono()
                }
            }
        })
    }

    companion object {
        val newInstance : MonoRecyclerFragment = MonoRecyclerFragment()
    }
}