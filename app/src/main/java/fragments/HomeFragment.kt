package fragments

import utils.api.ApiClient
import adapters.FollowerAdapter
import adapters.ItemPostRecyclerViewAdapter
import adapters.PostsAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bean.business.FollowerBean
import bean.business.PostBean
import retrofit2.Call
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Response
import bean.rest.FollowerResponse
import bean.rest.PostResponse
import utils.extensions.prefs
import retrofit2.Callback

class HomeFragment : Fragment() {

    private lateinit var itemsPost: List<PostBean>
    private lateinit var itemsFollow: List<FollowerBean>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callFollower()
        callPost()
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

    fun callPost(){
        ApiClient.getClient.getPosts(prefs.profileId!!).enqueue(object:  Callback<PostResponse>{
            override fun onResponse(
                call: Call<PostResponse>,
                response: Response<PostResponse>
            ) {
                val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                homePostRecyclerView.layoutManager = linearLayoutManager
                homePostRecyclerView.adapter = PostsAdapter(response.body()!!.payload!!)
                Log.i("info", response.body()!!.result.toString())
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                println("Chiamata non eseguita!!!! $t")
            }
        })
    }

    companion object {
        var homeFragment : HomeFragment = HomeFragment()
    }
}