package com.example.instagrammo.view.views.profile

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.components.topBar.TopBarBackComponent
import com.example.instagrammo.R
import com.example.instagrammo.beans.business.profile.*
import com.example.instagrammo.beans.rest.profile.edit.EditProfileRequest
import com.example.instagrammo.beans.rest.profile.ProfileResponse
import com.example.instagrammo.prefs
import com.example.instagrammo.environment.networking.ApiClient
import com.example.instagrammo.utils.CircleTransform
import com.example.instagrammo.view.viewmodel.DataState
import com.example.instagrammo.utils.ElementViewConverter.toEditable
import com.example.instagrammo.view.viewmodel.MainStateEvent
import com.example.instagrammo.view.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_modifica_profilo.*
import kotlinx.android.synthetic.main.fragment_modifica_profilo.view.*
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

    private val viewModel = MainViewModel()

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

    private fun setObservable() {


        viewModel.dataStateProfile.observe(viewLifecycleOwner, Observer { dataStateProfile ->
            when (dataStateProfile) {
                is DataState.Error -> {
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> {
                    itemsProfile = dataStateProfile.data
                    populateDataView()
                }
            }
        })

        viewModel.dataStateEditProfile.observe(viewLifecycleOwner, Observer { dataStateEditProfile ->
            when (dataStateEditProfile) {
                is DataState.Error -> {
                }
                is DataState.Loading -> {
                }
                is DataState.Success -> { listener.removeFragmentListener() }
            }
        })

      /*
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
        */
    }

    fun verificaDatiInseriti() {

        var nomeProfilo: String =
            if (NomeProfilo.text.isNullOrEmpty()) itemsProfile.name!! else NomeProfilo.text.toString()
        var descrizioneProfilo: String =
            if (Descrizione.text.isNullOrEmpty()) itemsProfile.description!! else Descrizione.text.toString()

        editProfileRequest =
            EditProfileRequest(
                prefs.rememberIdProfile,
                nomeProfilo,
                descrizioneProfilo,
                itemsProfile.picture
            )

        viewModel.setStateEvent(MainStateEvent.PutEditProfileEvent(editProfileRequest))

        setObservable()

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
        //bottone salva
        mView.close_edit_profile_button.setOnClickListener {
            verificaDatiInseriti()
        }
        //bottone back nell header
        mView.topBarBackComponent.setOnPressedListener{
            listener.removeFragmentListener()
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