package com.example.instagrammo.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.example.instagrammo.R
import com.example.instagrammo.adapter.AddPostAdapter
import com.example.instagrammo.fragment.secondFragment.AddPostFragment
import com.example.instagrammo.network.second_network.PicApiClient
import com.example.instagrammo.response.PictureResponse
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.picture.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddFragment : Fragment() {

    private var pictures: List<PictureResponse> = mutableListOf()

    var photo : String = ""
    val gridLayoutManager = GridLayoutManager(context, 3)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPictureRandom()
        recycler_add_grid.layoutManager = gridLayoutManager




    }

    fun makeTransaction(picture : PictureResponse) {
        val fragmentManager = activity!!.supportFragmentManager
        var transaction: FragmentTransaction = fragmentManager?.beginTransaction()!!

        AddPostFragment.image = picture
        transaction.add(R.id.fragment_container, AddPostFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun getPictureRandom() {
        PicApiClient.getClient.getPicture(1, 60).enqueue(object : Callback<List<PictureResponse>> {
            override fun onFailure(call: Call<List<PictureResponse>>, t: Throwable) {
                Log.i("info errore", "${t.message}")
            }

            override fun onResponse(
                call: Call<List<PictureResponse>>,
                response: Response<List<PictureResponse>>
            ) {
                if (!response.body().isNullOrEmpty()) {
                    pictures = response.body()?.toMutableList()!!

                    val addPostAdapter = AddPostAdapter(pictures)
                    recycler_add_grid.adapter = addPostAdapter

                    addPostAdapter.setOnImageClickListner { picture -> photo = picture.url  }
                    addPostAdapter.setOnImageClickListner { picture ->  makeTransaction(picture) }
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