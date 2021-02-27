package com.example.instagrammo.view.views.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.lorem.LoremBean
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_post.*

class AddPostConfirmFragment : Fragment(){

    private lateinit var idImage : String

    private lateinit var listenerImage: OnImageItemClickListener

    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = this.arguments
        idImage = bundle?.getString("idImageLorem").toString()

        return inflater.inflate(R.layout.fragment_add_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //viewModel.setStateEvent(MainStateEvent.GetLoremImageEvent(idImage,"600","600"))
        //setObservable()
        populateDataView()
        setButtonListener()

    }

    private fun setObservable() {

        viewModel.dataStateLoremImage.observe(viewLifecycleOwner, Observer { dataStatePosts ->
            when (dataStatePosts) {
                is DataState.Error -> {
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> {
                   //image = dataStatePosts.data
                    //populateDataView()
                    //Log.i("DATAIMAGE ", image.download_url)
                }
            }
        })
    }

    private fun setButtonListener() {
        confirm_floating_button.setOnClickListener {
            AddPostFragment.newInstance(image)
            listenerImage.onImageItemAddPostListener(image)
        }
    }

    private fun populateDataView() {
        Picasso.get().load(image.download_url)
            .resize(1300, 1900)
            .centerCrop()
            .onlyScaleDown()
            .into(image_lorem)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnImageItemClickListener) {
            listenerImage = context
        } else {
            throw RuntimeException("$context must implement OnImageItemClickListener")
        }
    }


    companion object {

        private lateinit var image: LoremBean

        fun newInstance(image: LoremBean)  : AddPostConfirmFragment = AddPostConfirmFragment().also{ this.image = image}
    }
}