package com.costa.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.costa.`interface`.ApiInterface
import com.costa.adapter.PostAdapter
import com.costa.instagrammo.R
import com.costa.servizi.ApiClient
import com.costa.servizi.ApiClient.userId
import com.costa.servizi.PostsResponse
import com.costa.servizi.ProfileResponse
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_post_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiClient.getClient.getProfile(userId).enqueue(object : Callback<ProfileResponse> {

            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                Picasso.get()
                    .load(response.body()!!.payload!![0].picture)
                    .transform(CropCircleTransformation())
                    .into(img_profilo)
                tv_nome_Profilo.text=response.body()!!.payload!![0].name
                tv_numero_followers.text= response.body()!!.payload!![0].followersNumber.toString()
                tv_numero_posts.text=response.body()!!.payload!![0].postsNumber.toString()
                tv_descrizione_Profilo.text=response.body()!!.payload!![0].description

            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Toast.makeText(context, "non riusciamo a caricare le informazioni, riprova piu tardi", Toast.LENGTH_SHORT).show()
            }



        })

    }
}