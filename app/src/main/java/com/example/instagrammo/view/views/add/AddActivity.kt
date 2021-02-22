package com.example.instagrammo.view.views.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.utils.adapter.ItemPostRecyclerViewAdapter
import com.example.instagrammo.utils.adapter.OnPostItemClickListener
import com.example.instagrammo.view.views.profile.ButtonEditProfileListener
import com.example.instagrammo.views.BaseActivity
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.recycler_post_mono_view

class AddFragment : Fragment(){

    private lateinit var mView: View

    private lateinit var itemsPost: List<PostBean>

    private var listenerPost: OnPostItemClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_add, container, false)
        setAdapterGrid()
        return this.mView
    }


    private fun setAdapterGrid() {
        val recyclerView = this.mView.recycler_add_grid_view
        if (recyclerView is RecyclerView) {
            recyclerView.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = ItemPostRecyclerViewAdapter(this.context, itemsPost, listenerPost, true )
            }
        }
    }

    companion object {
        var newInstance : AddFragment = AddFragment()
    }
}