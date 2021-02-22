package fragments

import adapters.*
import utils.api.ApiClient
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bean.business.Follower
import bean.rest.FollowerProfile
import retrofit2.Call
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Response
import bean.rest.FollowerResponse
import bean.rest.Post
import bean.rest.PostResponse
import kotlinx.android.synthetic.main.fragment_home.view.*
import utils.extensions.prefs
import retrofit2.Callback

class HomeFragment : Fragment() {

    private lateinit var mView: View
    private var listenerPost: OnPostItemClickListener? = null
    private var listenerFollow: OnFollowItemClickListener? = null
    private lateinit var itemsPost: List<Post>
    private lateinit var itemsFollow: List<FollowerProfile>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.mView = inflater.inflate(R.layout.fragment_home, container, false)

        return this.mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callFollower()
    }

    fun callFollower(){
        ApiClient.getClient.getFollowers(prefs.profileId!!).enqueue(object:  Callback<FollowerResponse>{
            override fun onResponse(
                    call: Call<FollowerResponse>,
                    response: Response<FollowerResponse>
            ) {
                val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                homeFollowerRecyclerView.layoutManager = linearLayoutManager
                homeFollowerRecyclerView.adapter = FollowerAdapter(response.body()!!.payload!!)
                Log.i("info", response.body()!!.result.toString())
            }
            override fun onFailure(call: Call<FollowerResponse>, t: Throwable) {
                println("Chiamata non eseguita!!!! $t")
            }
        })
    }

    private fun getData() {
        ApiClient.getClient.getPosts(prefs.rememberPassword!!)
                .enqueue(object : Callback<PostResponse> {
                    override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                        Log.i("INFORMATION", t.message.toString())
                    }

                    override fun onResponse(
                            call: Call<PostResponse>,
                            response: Response<PostResponse>
                    ) {
                        //items = response.body()?.payload!!.toMutableList()
                        //Log.i("INFORMATION --> vediamo", items.toString())
                    }
                })
    }

    private fun setAdapter() {
        val recyclerView = this.mView.findViewById<RecyclerView>(R.id.homePostRecyclerView)
        if (recyclerView is RecyclerView ) {
            recyclerView.apply{
                layoutManager = LinearLayoutManager(context)
                adapter = ItemPostRecyclerViewAdapter(this.context, itemsPost, listenerPost )
            }
        }
    }

    private fun setFollowAdapter() {
        val recyclerView = this.mView.homeFollowerRecyclerView
        if (recyclerView is RecyclerView) {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ItemFollowRecyclerViewAdapter(this.context, itemsFollow, listenerFollow)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnPostItemClickListener) {
            listenerPost = context
        } else {
            throw RuntimeException("$context we will need to implement later on OnPostItemClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerPost = null
    }

    companion object {
        var homeFragment : HomeFragment = HomeFragment()
    }
}