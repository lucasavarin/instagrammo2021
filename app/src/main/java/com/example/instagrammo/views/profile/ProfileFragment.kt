package com.example.instagrammo.views.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.beans.posts.Profile
import com.example.instagrammo.prefs
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private lateinit var itemsProfile: Profile

    private lateinit var viewContext: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewContext = inflater.inflate(R.layout.fragment_profile, container, false)
        getData()

        return viewContext
    }

    private fun getData() {
        ApiClient.GetClient.getProfile(prefs.rememberIdProfile)
            .enqueue(object : Callback<Profile> {
                override fun onFailure(call: Call<Profile>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                    itemsProfile = response.body()!!
                    populateDataView()
                }
            })
    }

    private fun populateDataView() {
        Picasso.get().load(R.drawable.bird).resize(1000,1000).transform(CircleTransform()).into(viewContext.profileImage)
        viewContext.postsNumber.text = "2"//itemsProfile.postsNumber
        viewContext.followersNumber.text = "1"//itemsProfile.followersNumber
        viewContext.name.text = "Jherome Samson"//itemsProfile.name
        viewContext.description.text = "SERVER RESPONSE IS EMPTY"//itemsProfile.description
/*
        if (itemsProfile.description.isNullOrBlank())
            viewContext.description.visibility = View.GONE

 */
    }

    companion object {
        var newInstance : ProfileFragment = ProfileFragment()
    }
}