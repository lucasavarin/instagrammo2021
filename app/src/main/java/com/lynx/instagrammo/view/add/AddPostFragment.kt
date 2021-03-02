package com.lynx.instagrammo.view.add

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lynx.instagrammo.R
import com.lynx.instagrammo.beanRest.PicsumImageRest
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.networking.AddPostRequest
import com.lynx.instagrammo.prefs
import com.lynx.instagrammo.view.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_navigation_header.*
import kotlinx.android.synthetic.main.fragment_add_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostFragment : Fragment() {
    lateinit private var listener: AddPostFragmentInterface

    var title: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edit_back_button.setOnClickListener {
            listener.backToShowImage()
        }

        Picasso
            .get()
            .load(itemPicture!!.download_url)
            .transform(CircleTransform())
            .into(image_new_post)



        btn_add_new_post.setOnClickListener {
            title = description_new_post.text.toString()
            ApiClient.GetClient.addPost(
                prefs.userId, AddPostRequest(
                    profileId = prefs.userId,
                    title = title,
                    picture = transformPicture(itemPicture!!)
            )
            ).enqueue(object : Callback<Boolean> {
                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    Toast.makeText(context, "POST SALVATO", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.i("ERROR", "errore nella chiamata addPost")
//                    Toast.makeText(context, "ERRORE NELLA CHIAMATA", Toast.LENGTH_SHORT).show()
                }

            })

            listener.addPostAndExit()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AddPostFragmentInterface)
            listener = context
    }



    companion object {

        var itemPicture: PicsumImageRest? = null
        val newInstance: AddPostFragment = AddPostFragment()

        fun newInstace(item: PicsumImageRest): AddPostFragment {
            itemPicture = item
            return AddPostFragment()
        }
    }

    interface AddPostFragmentInterface {
        fun addPostAndExit()
        fun backToShowImage()
    }

    fun transformPicture(image: PicsumImageRest): String {
        val picsumUrl: List<String>
        var result = ""
        picsumUrl = image.download_url.split("/")
        picsumUrl.forEach {
            if (picsumUrl[5].equals(it) || picsumUrl[6].equals(it)) {
                result = result + "/" + "500"
            } else if (!result.isNullOrBlank()) {
                result = result + "/" + it
            }else{
                result = it
            }
        }
        Log.i("RESULT :", result)
        return result
    }
}