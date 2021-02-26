package com.example.instagrammo.view.views.add

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.lorem.LoremBean
import com.example.instagrammo.beans.rest.post.AddPostRequest
import com.example.instagrammo.prefs
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_confirm_add_post.*

class AddPostFragment: Fragment() {

    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_add_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populateDataView()
        confirmButtonListener()
    }

    private fun confirmButtonListener() {
        confirm_post_image_button.setOnClickListener {
            setAddRequest()
            sendRequest()
            setObserver()
        }
    }

    private fun setAddRequest() : AddPostRequest = AddPostRequest(
        prefs.idProfile,
        description_post.toString(),
        image.download_url)


    private fun sendRequest() {
        viewModel.setStateEvent(MainStateEvent.PostAddPicture(prefs.idProfile,setAddRequest()))
    }

    private fun setObserver() {
        viewModel.dataStateAddPost.observe(this.viewLifecycleOwner, Observer { dataState ->
            when(dataState) {
                is DataState.Error ->  {}
                is DataState.Loading -> { }
                is DataState.Success -> { Log.i("debug",dataState.data.toString())}
                }
            }
        )
    }

    private fun populateDataView() {
        Picasso.get().load(image.download_url).into(image_to_confirm)
    }

    companion object {

        private lateinit var image: LoremBean

        fun newInstance(image: LoremBean)  : AddPostFragment = AddPostFragment().also{ this.image = image}
    }
}