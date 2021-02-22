package com.example.instagrammo.fragment.secondFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.instagrammo.R
import com.example.instagrammo.bean.Post
import com.example.instagrammo.fragment.ProfileFragment
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.request.EditProfileRequest
import com.example.instagrammo.response.PayloadProfile
import com.example.instagrammo.response.PostResponse
import com.example.instagrammo.response.ProfileResponse
import com.example.instagrammo.view.CircleTransformation
import com.example.instagrammo.view.prefs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_modify_profile.*
import kotlinx.android.synthetic.main.fragment_modify_profile.user_image
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ModifyProfileFragment : Fragment() {
    private var posts: List<PayloadProfile> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modify_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetailsProfile()

        icon_check.setOnClickListener {
            var idImage = image_user_id.text
            var path = "https://picsum.photos/id/$idImage/300/300"
            Picasso.get().load(path).transform(CircleTransformation()).into(user_image)
        }
        salva_button.setOnClickListener {
            var idImage = image_user_id.text
            var path = "https://picsum.photos/id/$idImage/300/300"
            ApiClient.getClient.saveChanges(
                prefs.idProfilo,
                EditProfileRequest(
                    prefs.idProfilo,
                    text_nome_profile.text.toString(),
                    descrizione.text.toString(),
                    path
                )
            ).enqueue(object : Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.i("info","${t.message}")
                }

                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    Toast.makeText(context, "Modifiche Salvate", Toast.LENGTH_LONG).show()
                }

            })
        }
    }

    fun getDetailsProfile() {
        ApiClient.getClient.getSingleProfile(prefs.idProfilo)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.i("info", "${t.message}")
                }

                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        posts = response.body()?.payload!!
                        Picasso.get().load(posts[0].picture).transform(CircleTransformation())
                            .into(user_image)

                        image_user_id.setText(posts[0].profileId)
                        text_nome_profile.setText(posts[0].name)
                        descrizione.setText(posts[0].description)
                    }
                }

            })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ModifyProfileFragment().apply {
                arguments = Bundle().apply {}
            }
    }

}