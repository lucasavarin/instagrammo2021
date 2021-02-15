package com.lynx.instagrammo.view.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.Profile
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.networking.PostResponse
import com.lynx.instagrammo.networking.ProfileResponse
import com.lynx.instagrammo.prefs
import com.lynx.instagrammo.view.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        ApiClient.GetClient.getSingleProfile(prefs.userId).enqueue(object : Callback<ProfileResponse> {

            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful) {
                    profile_name_text.text = response.body()!!.payload!![0].name
                    profile_description_text.text = response.body()!!.payload!![0].description
                    followers_count_text.text = response.body()!!.payload!![0].followersNumber
                    posts_count_text.text = response.body()!!.payload!![0].postNumber
                    Picasso.get().load(response.body()!!.payload!![0].picture).transform(CircleTransform()).into(profile_image)
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.i("ONFALIURE" , t.message)
            }
        })

    }
    }

