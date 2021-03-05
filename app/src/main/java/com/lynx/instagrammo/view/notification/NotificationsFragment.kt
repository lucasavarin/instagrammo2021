package com.lynx.instagrammo.view.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lynx.instagrammo.R
import com.lynx.instagrammo.bean.Post
import com.lynx.instagrammo.bean.converter.PostConverter
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.networking.PostResponse
import com.lynx.instagrammo.prefs
import com.lynx.instagrammo.view.recyclerView.NotificationListAdapter
import kotlinx.android.synthetic.main.fragment_notifications.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsFragment : Fragment() {

    lateinit var callGetPost: Call<PostResponse>

    companion object {
        val newInstance: NotificationsFragment = NotificationsFragment()
    }

    //onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    //onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPost()
    }

    //onDestroy
    override fun onDestroy() {
        super.onDestroy()
        callGetPost.cancel()
    }

    fun linearLayoutManager(payload: List<Post>) {
        recycler_notification.apply {
            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycler_notification.layoutManager = linearLayoutManager
            recycler_notification.adapter = NotificationListAdapter(payload)
        }
    }

    fun getPost() {
        callGetPost = ApiClient.GetClient.getPost()
        callGetPost.enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                linearLayoutManager(PostConverter.restToBusiness(response.body()!!.payload!!.takeLast(prefs.postsDifference)))
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {

            }

        })
    }

}