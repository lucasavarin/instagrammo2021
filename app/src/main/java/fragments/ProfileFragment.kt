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
import bean.rest.ProfileResponse
import com.example.instagrammo.R
import com.squareup.picasso.Picasso
import interfaces.ButtonEditProfileListener
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.api.ApiClient
import utils.extensions.CircleTransformation
import utils.extensions.prefs

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
        callDatasProfile()
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

    fun callDatasProfile(){
        ApiClient.getClient.getProfile(prefs.profileId!!).enqueue(object: Callback<ProfileResponse>{
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                Picasso.get().load(response.body()!!.payload!![0].picture).transform(CircleTransformation()).into(profileImage)
                post.text = response.body()!!.payload!![0].postsNumber.toString()
                follower.text = response.body()!!.payload!![0].followersNumber.toString()
                name.text = response.body()!!.payload!![0].name
                description.text = response.body()!!.payload!![0].description
            }
            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                println("Chiamata non eseguita, postProfilo non presi!!!! $t")
            }
        })
    }

    fun callPostProfile(){
        ApiClient.getClient.posts(prefs.profileId!!).enqueue(object: Callback<PostResponse> {
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
                println("Chiamata non eseguita, postProfilo non presi!!!! $t")
            }
        })
    }

}