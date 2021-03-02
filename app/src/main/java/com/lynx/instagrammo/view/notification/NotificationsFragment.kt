package com.lynx.instagrammo.view.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.MyPost
import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.networking.PostResponse
import com.lynx.instagrammo.prefs
import com.lynx.instagrammo.view.recyclerView.NotificationListAdapter
import com.lynx.instagrammo.view.recyclerView.ProfileListAdapter
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.recycler_profileImg_list
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* toDO cambiare il nome */
class NotificationsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiClient.GetClient.getPost().enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
               linearLayoutManager(response.body()!!.payload!!.takeLast(prefs.postsDifference))
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {

            }

        })
    }
    companion object{
        val newInstance: NotificationsFragment = NotificationsFragment()
    }

    private fun linearLayoutManager(payload: List<Post>) {
        recycler_notification.apply {
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycler_notification.layoutManager = linearLayoutManager
            recycler_notification.adapter = NotificationListAdapter(payload)
        }
    }
}