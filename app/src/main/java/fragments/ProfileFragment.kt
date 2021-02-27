package fragments

import adapters.PostsAdapter
import android.content.Context
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
import interfaces.ButtonEditProfileListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.api.ApiClient
import utils.extensions.prefs
import java.util.*

class ProfileFragment : Fragment() {

    private var listenerButtonEdit: ButtonEditProfileListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editButton.setOnClickListener {
            listenerButtonEdit?.onClickListenerEditButtonProfile(true)
        }
        callPostProfile()
    }

    companion object {
        var newInstance: ProfileFragment = ProfileFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ButtonEditProfileListener) {
            listenerButtonEdit = context
        }
    }

    fun callPostProfile(){
        ApiClient.getClient.getPosts(prefs.profileId!!).enqueue(object: Callback<PostResponse> {
            override fun onResponse(
                call: Call<PostResponse>,
                response: Response<PostResponse>
            ) {
                val gridLayoutManager = GridLayoutManager(context, 3)
                gridRecyclerView.layoutManager = gridLayoutManager
                gridRecyclerView.adapter = PostsAdapter(response.body()!!.payload!!)
                gridImageButton.setOnClickListener{
                    gridRecyclerView.setVisibility(View.VISIBLE)
                    monoRecyclerView.setVisibility(View.INVISIBLE)
                }

                val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                monoRecyclerView.layoutManager = linearLayoutManager
                monoRecyclerView.adapter = PostsAdapter(response.body()!!.payload!!)
                monoImageButton.setOnClickListener{
                    gridRecyclerView.setVisibility(View.INVISIBLE)
                    monoRecyclerView.setVisibility(View.VISIBLE)
                }

                Log.i("info", response.body()!!.result.toString())
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                println("Chiamata non eseguita!!!! $t")
            }
        })
    }

}