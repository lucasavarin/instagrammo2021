package com.costa.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.costa.beans.PicSumImage
import com.costa.beans.PicSumImageOut
import com.costa.instagrammo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_post_step_1.*
import kotlinx.android.synthetic.main.item_profile_post_grid.view.*

class AddPostStep1Fragment : Fragment() {
    companion object {
        lateinit var imagePost: PicSumImage
        val instance: AddPostStep1Fragment = AddPostStep1Fragment()
        fun getinstance(image: PicSumImage): AddPostStep1Fragment {
            imagePost = image
            return instance
        }

    }


    lateinit var listener: AddPostStep1Interface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =// Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_add_post_step_1, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddPostStep1Interface) {
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load(imagePost.download_url)
            .into(iv_imagine_add)

        bt_next.setOnClickListener {
            listener.addPostStep1ToStep2(imagePost)
        }

        header_add_post_step_1.setOnBackPressedListener {
            listener.backToAddFragment()
        }
    }

    interface AddPostStep1Interface {
        fun addPostStep1ToStep2(image: PicSumImage)
        fun backToAddFragment()
    }

}