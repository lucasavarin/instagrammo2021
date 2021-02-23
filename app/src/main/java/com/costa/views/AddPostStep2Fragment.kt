package com.costa.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.costa.beans.PicSumImage
import com.costa.instagrammo.R
import com.costa.servizi.AddPostRequestRest
import com.costa.servizi.ApiClient
import com.costa.servizi.BooleanRespomce
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add_post_step_2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostStep2Fragment : Fragment() {
    companion object {
        lateinit var imagePost: PicSumImage
        val instance: AddPostStep2Fragment = AddPostStep2Fragment()
        fun getinstance(image: PicSumImage): AddPostStep2Fragment {
            imagePost = image
            return instance
        }
    }


    lateinit var listener: AddPostStep2Interface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =// Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_add_post_step_2, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddPostStep2Interface)
            listener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load(imagePost.imageFormated)
            .into(iv_imagine_post)
        header_add_post_step_2.setOnBackPressedListener {
            listener.backToAddPostStep1()
        }
        btn_carica_post.setOnClickListener {

            var post: AddPostRequestRest = AddPostRequestRest(
                profileId = ApiClient.userId,
                title = editDescrizione.text.toString(),
                picture = imagePost.imageFormated
            )

            ApiClient.getClient.doAddPost(post).enqueue(object : Callback<BooleanRespomce> {
                override fun onFailure(call: Call<BooleanRespomce>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<BooleanRespomce>,
                    response: Response<BooleanRespomce>
                ) {
                    listener.salvaPost()
                }

            })
        }
    }

    interface AddPostStep2Interface {
        fun salvaPost()
        fun backToAddPostStep1()
    }

}