package com.example.instagrammo.views.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.beans.profile.EditProfileResponse
import com.example.instagrammo.beans.profile.EditProfileRequest
import com.example.instagrammo.beans.profile.Profile
import com.example.instagrammo.beans.profile.ProfileResponse
import com.example.instagrammo.prefs
import com.example.instagrammo.retrofit.ApiClient
import kotlinx.android.synthetic.main.fragment_modifica_profilo.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileFragment : Fragment() {

    private lateinit var itemsProfile: Profile

    private lateinit var editProfileRequest: EditProfileRequest

    private lateinit var listener : EditProfileFragmentListener

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

    fun verificaDatiInseriti(){

        var nomeProfilo : String = if(NomeProfilo.text.isNullOrEmpty()) itemsProfile.name!! else NomeProfilo.text.toString()
        var descrizioneProfilo : String = if(Descrizione.text.isNullOrEmpty()) itemsProfile.description!! else Descrizione.text.toString()

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
                    itemsProfile = response.body()?.payload?.get(0)!!
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

        companion object {
        var newInstance : EditProfileFragment = EditProfileFragment()
    }

}