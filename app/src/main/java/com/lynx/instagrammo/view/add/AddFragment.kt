package com.lynx.instagrammo.view.add

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.lynx.instagrammo.R
import com.lynx.instagrammo.beanRest.PicsumImageRest
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.view.home.HomeFragment
import com.lynx.instagrammo.view.recyclerView.AddGridAdapter
import com.lynx.instagrammo.view.recyclerView.AddGridViewHolder
import kotlinx.android.synthetic.main.fragment_add.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFragment : Fragment() {

    lateinit private var listener: AddFragmentInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callGetListImage()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is AddFragmentInterface)
            listener = context
    }

    companion object {
        val newInstance: AddFragment = AddFragment()
    }

    private fun callGetListImage() {
        ApiClient.GetPicsumClient.getImage().enqueue(object : Callback<Array<PicsumImageRest>> {
            override fun onResponse(call: Call<Array<PicsumImageRest>>, response: Response<Array<PicsumImageRest>>) {
                gridLayoutManager(response!!.body()!!.toList())
            }

            override fun onFailure(call: Call<Array<PicsumImageRest>>, t: Throwable) {
                Log.i("ERRORE", "la chiamata getImage va in errore")
            }

        })
    }

        private fun gridLayoutManager(payload: List<PicsumImageRest>) {
            recycler_add_image.apply {
                val gridLayoutManager = GridLayoutManager( context, 3)
                layoutManager = gridLayoutManager
                adapter = AddGridAdapter(payload){image, position ->
                    listener.goToAddImage(image)
                }
            }
        }

    interface AddFragmentInterface{
        fun goToAddImage(item: PicsumImageRest)
    }

}