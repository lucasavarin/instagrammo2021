package com.example.instagrammo.view.views.recycler

import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel

class ViewModelRecyclerView() {

    private val viewModel = MainViewModel()

    val dataState = viewModel.dataStatePostProfile

    val data = viewModel.setStateEvent(MainStateEvent.GetPostsProfileEvent)
}