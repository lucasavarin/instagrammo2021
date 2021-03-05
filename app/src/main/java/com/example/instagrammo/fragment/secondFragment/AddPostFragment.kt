package com.example.instagrammo.fragment.secondFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.instagrammo.R
import com.example.instagrammo.fragment.AddFragment
import com.example.instagrammo.interfaces.InterfaceBack
import com.example.instagrammo.network.second_network.PicApiClient
import com.example.instagrammo.response.PictureResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.add_post.*
import kotlinx.android.synthetic.main.add_post.customViewController
import kotlinx.android.synthetic.main.add_post.view.*
import kotlinx.android.synthetic.main.conferm_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostFragment() : Fragment() {
    private  var listner : InterfaceBack? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.add_post, container, false)

        Picasso.get().load(image.download_url).fit().into(view.image_add)
        return view


    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InterfaceBack) {
            listner = context
        } else {
            throw RuntimeException("$context must implement InterfaceBack")
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrow.setOnClickListener {
            makeTransaction(image)
        }
        customViewController.setOnBackPressedListner {
            listner?.back(this)
        }
    }

    fun makeTransaction(picture: PictureResponse) {
        val fragmentManager = activity!!.supportFragmentManager
        var transaction: FragmentTransaction = fragmentManager?.beginTransaction()!!
        ConfirmPostFragment.image = picture
        transaction.add(R.id.fragment_container, ConfirmPostFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        var instanza: AddPostFragment = AddPostFragment()
        lateinit var image: PictureResponse
    }

}