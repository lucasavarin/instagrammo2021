package com.lynx.instagrammo.view.add

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lynx.instagrammo.R
import com.lynx.instagrammo.beanRest.PicsumImageRest
import com.lynx.instagrammo.view.edit.EditFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_navigation_header.*
import kotlinx.android.synthetic.main.fragment_show_image.*

class ShowImageFragment : Fragment() {

    private lateinit var listener: ShowImageFragmentInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit_back_button.setOnClickListener {
            listener.backToAdd()
        }

        Picasso
            .get()
            .load(itemPicture!!.download_url)
            .into(show_image_detail)

        add_image_tosave.setOnClickListener {
            listener.goToAddPost(itemPicture!!)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ShowImageFragmentInterface)
            listener = context
    }

    companion object {
        var itemPicture: PicsumImageRest? = null
        val newInstance: ShowImageFragment = ShowImageFragment()
        fun newInstance(item: PicsumImageRest): ShowImageFragment {
            itemPicture = item
            return newInstance
        }
    }

    interface ShowImageFragmentInterface {
        fun goToAddPost(item: PicsumImageRest)
        fun backToAdd()

    }
}