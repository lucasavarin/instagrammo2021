package com.costa.views.main.addpost

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.costa.adapter.addpost.AddPostsGridAdAdapter
import com.costa.beans.rest.PicSumImageOut
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
import kotlinx.android.synthetic.main.fragment_add.*
import android.util.Log
import com.costa.beans.business.PicSumImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFragment : Fragment() {
    companion object {
        var instance: AddFragment =
            AddFragment()

    }

    lateinit var listener: AddFragmentInterface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddFragmentInterface)
            listener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiClient.getImageClient.getPictures().enqueue(object : Callback<Array<PicSumImageOut>> {
            override fun onFailure(call: Call<Array<PicSumImageOut>>, t: Throwable) {
                Log.i("ERRORE: ", "onFailure")

            }

            override fun onResponse(
                call: Call<Array<PicSumImageOut>>,
                response: Response<Array<PicSumImageOut>>
            ) {
                gredRecycleView(response.body()!!.toList())
            }


        })
    }

    fun gredRecycleView(payload: List<PicSumImageOut>) {

        rv_add_post.apply {
            val layoutManager = GridLayoutManager(context, 3)
            rv_add_post.layoutManager = layoutManager
            rv_add_post.adapter =
                AddPostsGridAdAdapter(payload) { image ->
                    listener.onClickImage(image)
                }
        }
    }

    interface AddFragmentInterface {
        fun onClickImage(imageOut: PicSumImage)
    }
}