package com.example.instagrammo.views.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.beans.profile.*
import com.example.instagrammo.prefs
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.utils.ElementViewConverter.toEditable
import com.example.instagrammo.views.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_modifica_profilo.*
import kotlinx.android.synthetic.main.fragment_modifica_profilo.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.edit_profile_button
import kotlinx.android.synthetic.main.fragment_profile.view.profileImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileFragment : Fragment() {

    private lateinit var itemsProfile: ProfileBean

    private lateinit var editProfileRequest: EditProfileRequest

    private lateinit var listener: EditProfileFragmentListener

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_modifica_profilo, container, false)
        getData()
        buttonsListener()

        return mView
    }

    private fun putProfile() {
        ApiClient.GetClient.putProfile(editProfileRequest)
            .enqueue(object : Callback<EditProfileResponse> {
                override fun onFailure(call: Call<EditProfileResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(call: Call<EditProfileResponse>, response: Response<EditProfileResponse>) {
                    if (response.body()?.result!!) {
                       listener.removeFragmentListener()
                    }
                }
            })
    }

    fun verificaDatiInseriti() {

        var nomeProfilo: String =
            if (NomeProfilo.text.isNullOrEmpty()) itemsProfile.name!! else NomeProfilo.text.toString()
        var descrizioneProfilo: String =
            if (Descrizione.text.isNullOrEmpty()) itemsProfile.description!! else Descrizione.text.toString()

        editProfileRequest = EditProfileRequest(
            prefs.rememberIdProfile,
            nomeProfilo,
            descrizioneProfilo,
            itemsProfile.picture
        )

        putProfile()

    }

    private fun getData() {
        ApiClient.GetClient.getProfile(prefs.rememberIdProfile)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.i("INFORMATION", t.message.toString())
                }

                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    itemsProfile = response.body()?.payload?.map {
                        ProfileBean.convert(it)
                    }!![0]
                    populateDataView()
                }
            })
    }

    private fun buttonsListener() {
        mView.edit_profile_button.setOnClickListener {
            verificaDatiInseriti()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EditProfileFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement EditProfileFragmentListener")
        }
    }

    private fun populateDataView() {
        Picasso.get().load(R.drawable.bird).resize(1000, 1000).transform(CircleTransform())
            .into(mView.profileImage)
        mView.IdImmagine.text = itemsProfile.profileId?.toEditable()
        mView.NomeProfilo.text = itemsProfile.name?.toEditable()
        mView.Descrizione.text = itemsProfile.description?.toEditable()
        /* mView.postsNumber.text = itemsProfile.postsNumber
         mView.followersNumber.text = itemsProfile.followersNumber
         mView.name.text = itemsProfile.name
         mView.description.text = itemsProfile.description*/
    }

    companion object {
        var newInstance: EditProfileFragment = EditProfileFragment()
    }


}