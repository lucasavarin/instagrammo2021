package com.example.instagrammo.view.views.add

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.lorem.LoremBean
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.utils.adapter.ItemLoremRecyclerViewAdapter
import com.example.instagrammo.utils.adapter.ItemPostRecyclerViewAdapter
import com.example.instagrammo.utils.adapter.OnPostItemClickListener
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.example.instagrammo.view.views.profile.ButtonEditProfileListener
import com.example.instagrammo.views.BaseActivity
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.recycler_post_mono_view

class AddFragment : Fragment(){

    private val viewModel = MainViewModel()
    private lateinit var listaLorem : List<LoremBean>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.setStateEvent(MainStateEvent.GetLoremImagesEvent)
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.dataStateLoremImages.observe(this.viewLifecycleOwner, Observer { dataState ->
            when(dataState) {
                is DataState.Error ->  {}
                is DataState.Loading -> { }
                is DataState.Success -> {
                    listaLorem = dataState.data
                    setAdapterGrid(view)
                }
            }
        })
    }

    private fun setAdapterGrid(mView : View) {
        val recyclerView = view?.recycler_add_grid_view
        if (recyclerView is RecyclerView) {
            recyclerView.apply{
                layoutManager = GridLayoutManager(context, 3)
                adapter = ItemLoremRecyclerViewAdapter(this.context, listaLorem )
            }
        }
    }


    companion object {
        var newInstance : AddFragment = AddFragment()
    }
}
