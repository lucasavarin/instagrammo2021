package com.example.instagrammo.fragment.secondFragment

import android.content.Context
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.instagrammo.InterfaceApp
import com.example.instagrammo.R
import com.example.instagrammo.fragment.ProfileFragment
import com.example.instagrammo.network.ApiClient
import com.example.instagrammo.request.EditProfileRequest
import com.example.instagrammo.response.PayloadProfile
import com.example.instagrammo.response.ProfileResponse
import com.example.instagrammo.view.CircleTransformation
import com.example.instagrammo.view.prefs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_modify_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ModifyProfileFragment : Fragment() {
    private  var listner : InterfaceApp? = null
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
                    Log.i("info", "${t.message}")
                }

                override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                    Toast.makeText(context, "Modifiche Salvate", Toast.LENGTH_LONG).show()
                }

            })
        }
        customViewController.setOnBackPressedListner {
            listner?.backToProfile()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is InterfaceApp) {
            listner = context
        } else {
            throw RuntimeException("$context must implement InterfaceApp")
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

    private fun closefragment() {
        val fragmentManager = activity!!.supportFragmentManager
        fragmentManager.beginTransaction().apply {
            remove(ModifyProfileFragment.newInstance)
            commit()
        }
    }

    companion object {
        var newInstance: ModifyProfileFragment = ModifyProfileFragment()

    }

}