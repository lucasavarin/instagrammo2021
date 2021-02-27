package fragments

import adapters.PostsAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bean.rest.PostResponse
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.gridRecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.api.ApiClient
import utils.extensions.prefs

class AddFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callAdd()
    }

    companion object {
        var addFragment : AddFragment = AddFragment()
    }

    fun callAdd(){
        ApiClient.getClient.getPosts(prefs.profileId!!).enqueue(object: Callback<PostResponse> {
            override fun onResponse(
                call: Call<PostResponse>,
                response: Response<PostResponse>
            ) {
                val gridLayoutManager = GridLayoutManager(context, 3)
                addRecyclerView.layoutManager = gridLayoutManager
                addRecyclerView.adapter = PostsAdapter(response.body()!!.payload!!)
                Log.i("info", response.body()!!.result.toString())
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                println("Chiamata non eseguita!!!! $t")
            }
        })
    }


}