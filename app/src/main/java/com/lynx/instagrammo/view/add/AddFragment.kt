package com.lynx.instagrammo.view.add

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lynx.instagrammo.R
import com.lynx.instagrammo.beanRest.PicsumImageRest
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.view.recyclerView.AddGridAdapter
import kotlinx.android.synthetic.main.fragment_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFragment : Fragment() {

    var page = 0
    var limit = 30
    var listOfPost = mutableListOf<PicsumImageRest>()
    lateinit var callGetPicsum: Call<Array<PicsumImageRest>>
    lateinit var listener: AddFragmentInterface

    companion object {
        val newInstance: AddFragment = AddFragment()
    }

    //onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    //onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = AddGridAdapter(listOfPost) { image, position ->
            listener.goToAddImage(image)
        }
        recycler_add_image.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    callGetListImage(adapter)
                }
            }
        })
        callGetListImage(adapter)
    }

    //onAttach
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddFragmentInterface)
            listener = context
    }

    //onDestroy
    override fun onDestroy() {
        super.onDestroy()
        callGetPicsum.cancel()
    }

    //Call get Picsum Image
    fun callGetListImage(adapter: AddGridAdapter) {
        page++
        callGetPicsum = ApiClient.GetPicsumClient.getImage(page, limit)

        callGetPicsum.enqueue(object : Callback<Array<PicsumImageRest>> {
            override fun onResponse(call: Call<Array<PicsumImageRest>>, response: Response<Array<PicsumImageRest>>) {
                listOfPost.addAll(response!!.body()!!.toMutableList())
                adapter.notifyDataSetChanged()
                gridLayoutManager(adapter)
                if (listOfPost.size > 30) {
                    recycler_add_image.scrollToPosition(listOfPost.size - 42)
                }

            }

            override fun onFailure(call: Call<Array<PicsumImageRest>>, t: Throwable) {
                Log.i("ERRORE", "la chiamata getImage va in errore")
            }

        })
    }

    // Layout Manager
    private fun gridLayoutManager(adapter: AddGridAdapter) {
        recycler_add_image.apply {
            val gridLayoutManager = GridLayoutManager(context, 3)
            recycler_add_image.layoutManager = gridLayoutManager
            recycler_add_image.adapter = adapter
        }
    }

    interface AddFragmentInterface {
        fun goToAddImage(item: PicsumImageRest)
    }

}