package com.example.instagrammo.fragment.secondFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.instagrammo.R
import com.example.instagrammo.fragment.HomeFragment
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.request.AddPostReq
import com.example.instagrammo.response.PictureResponse
import com.example.instagrammo.response.PostResult
import com.example.instagrammo.view.CircleTransformation
import com.example.instagrammo.view.prefs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.conferm_post.*
import retrofit2.Callback
import kotlinx.android.synthetic.main.conferm_post.view.*
import retrofit2.Call
import retrofit2.Response


class ConfirmPostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.conferm_post, container, false)
        Picasso.get().load(image.download_url).transform(CircleTransformation())
            .into(view.add_image)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        conferma_button.setOnClickListener {
            confirmPost()
        }
    }

    fun confirmPost() {
        val post = AddPostReq(
            profileId = prefs!!.idProfilo,
            title = descrizione_post.text.toString(),
            picture = image.download_url
        )
        ApiClient.getClient.addPost(post).enqueue(object : Callback<PostResult>{
            override fun onFailure(call: Call<PostResult>, t: Throwable) {
                println("errore----" + t.message)
            }

            override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                if(response.isSuccessful){
                    Toast.makeText(activity, "Post aggiunto", Toast.LENGTH_SHORT).show()
                    activity!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container,HomeFragment()).commit()
                }
            }

        })

    }


    companion object {
        var instanza: ConfirmPostFragment = ConfirmPostFragment()
        lateinit var image: PictureResponse
    }
}