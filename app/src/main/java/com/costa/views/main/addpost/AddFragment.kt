package com.costa.views.main.addpost

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.costa.adapter.addpost.AddPostsGridAdAdapter
import com.costa.beans.business.PicSumImage
import com.costa.beans.converter.PicSumImageConverter
import com.costa.beans.rest.PicSumImageOut
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
import kotlinx.android.synthetic.main.fragment_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddFragment : Fragment() {
    companion object {
        var instance: AddFragment =
            AddFragment()

    }

    lateinit var callImage: Call<Array<PicSumImageOut>>
    var poststoHolder = mutableListOf<PicSumImage>()
    val LIMIT = 30
    var page: Int = 0
    lateinit var listener: AddFragmentInterface
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add, container, false)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddFragmentInterface)
            listener = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        var adapter = AddPostsGridAdAdapter(poststoHolder) { image ->
            listener.onClickImage(image)
        }

        rv_add_post.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    callImages(adapter)

                }
            }
        })
        callImages(adapter)

    }

    override fun onDestroy() {
        callImage.cancel()
        super.onDestroy()
    }

    fun callImages(adapter: AddPostsGridAdAdapter) {
        page++
        callImage = ApiClient.getImageClient.getPictures(page, LIMIT)
        callImage.enqueue(object : Callback<Array<PicSumImageOut>> {
            override fun onFailure(call: Call<Array<PicSumImageOut>>, t: Throwable) {
                Log.i("ERRORE: ", "onFailure")

            }

            override fun onResponse(
                call: Call<Array<PicSumImageOut>>,
                response: Response<Array<PicSumImageOut>>
            ) {
                poststoHolder.addAll(PicSumImageConverter.restToBusiness(response.body()!!.toMutableList()))
                adapter.notifyDataSetChanged()
                gredRecycleView(adapter)
                if (poststoHolder.size > 30) {
                    rv_add_post.scrollToPosition(poststoHolder.size - 42)
                }
            }


        })
    }

    fun gredRecycleView(adapter: AddPostsGridAdAdapter) {


        rv_add_post.apply {
            val layoutManager = GridLayoutManager(context, 3)
            rv_add_post.layoutManager = layoutManager
            rv_add_post.adapter = adapter


        }
    }

    interface AddFragmentInterface {
        fun onClickImage(imageOut: PicSumImage)
    }
}