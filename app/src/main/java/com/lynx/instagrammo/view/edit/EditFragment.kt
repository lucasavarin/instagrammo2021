package com.lynx.instagrammo.view.edit

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lynx.instagrammo.R
import com.lynx.instagrammo.networking.API.ApiClient
import com.lynx.instagrammo.networking.ProfileRequest
import com.lynx.instagrammo.prefs
import com.lynx.instagrammo.view.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_navigation_header.*
import kotlinx.android.synthetic.main.fragment_edit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditFragment : Fragment() {
    var imageIdEdited: String = ""
    var nameEdited: String = ""
    var profileId: String = ""
    var descriptionEdited: String = ""
    var pathLoremPicsum: String = ""
    var nameHint: String = ""
    var descriptionHint: String = ""
    var pictureHint: String = ""
    private lateinit var listener: EditFragmenInterface

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getHint()

        edit_back_button.setOnClickListener {
            listener.backToProfile()
            getHint()
        }



        save_edit_profile.setOnClickListener {

            imageIdEdited = edit_id_image.text.toString()
            nameEdited = editTxt_name.text.toString()
            profileId = prefs.userId
            descriptionEdited = editTxt_description.text.toString()

            if (checkEditProfile(nameEdited, descriptionEdited, profileId)) {

                pathLoremPicsum = "https://picsum.photos/id/$imageIdEdited/200/300"
                ApiClient.GetClient.putEditProfile(prefs.userId, ProfileRequest(
                        profileId,
                        nameEdited,
                        descriptionEdited,
                        pathLoremPicsum
                )).enqueue(object : Callback<Boolean> {
                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {

                    }

                    override fun onFailure(call: Call<Boolean>, t: Throwable) {
                        Log.i("CALL FAIL", "chiamata upload errata")
                    }

                })
            }
            listener.saveAndExit()
        }

        find_img_btn.setOnClickListener{
            imageIdEdited = edit_id_image.text.toString()
            pathLoremPicsum ="https://picsum.photos/id/$imageIdEdited/200/300"
            Picasso
                    .get()
                    .load(pathLoremPicsum)
                    .transform(CircleTransform())
                    .resize(200,200)
                    .into(edit_profile_image)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is EditFragmenInterface)
            listener = context
    }

    companion object {
        val newInstance: EditFragment = EditFragment()
    }

    fun checkEditProfile(nameEdited: String, descriptionEdited: String, pathLoremPicsum: String): Boolean {
        if (nameEdited.isNullOrBlank()) {
            Log.i("NULL POINT", nameEdited)
            return false
        }
        if (descriptionEdited.isNullOrBlank()) {
            Log.i("NULL POINT", descriptionEdited)
            return false
        }
        if (pathLoremPicsum.isNullOrBlank()) {
            Log.i("NULL POINT", pathLoremPicsum)
            return false
        } else
            return true
    }

    fun putArguments(args: Bundle){
         nameHint = args.getString("name").toString()
         descriptionHint = args.getString("description").toString()
         pictureHint = args.getString("picture").toString()

    }

    fun getHint(){
        editTxt_name.setText(nameHint)
        editTxt_description.setText(descriptionHint)
        Picasso
                .get()
                .load(pictureHint)
                .transform(CircleTransform())
                .resize(200,200)
                .into(edit_profile_image)
    }

    interface EditFragmenInterface{
        fun saveAndExit()
        fun backToProfile()
    }

}
