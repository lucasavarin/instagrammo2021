package com.example.instagrammo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.instagrammo.R
import com.example.instagrammo.adapter.AddPostAdapter
import com.example.instagrammo.network.second_network.PicApiClient
import com.example.instagrammo.response.PayloadProfile
import com.example.instagrammo.response.PictureResponse
import kotlinx.android.synthetic.main.fragment_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddFragment : Fragment() {

    private var pictures: List<PictureResponse> = mutableListOf()

    val gridLayoutManager = GridLayoutManager(context, 3)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this com.example.instagrammo.fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPictureRandom()
    }

    fun getPictureRandom() {
        PicApiClient.getClient.getPicture(1,30).enqueue(object : Callback<List<PictureResponse>> {
            override fun onFailure(call: Call<List<PictureResponse>>, t: Throwable) {
                Log.i("info errore", "${t.message}")
            }

            override fun onResponse(
                call: Call<List<PictureResponse>>,
                response: Response<List<PictureResponse>>
            ) {
                if (!response.body().isNullOrEmpty()) {
                    pictures = response.body()?.toMutableList()!!
                    recycler_add_grid.layoutManager = gridLayoutManager
                    recycler_add_grid.adapter = AddPostAdapter(pictures)


                    Log.i("info response.body", response.body().toString())
                }
            }


        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AddFragment().apply {
                arguments = Bundle().apply {}
            }
    }

}