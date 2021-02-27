package com.example.instagrammo.view.viewmodel

import com.example.instagrammo.beans.business.lorem.LoremBean
import com.example.instagrammo.beans.rest.auth.AuthRequest
import com.example.instagrammo.beans.rest.post.AddPostRequest
import com.example.instagrammo.beans.rest.profile.edit.EditProfileRequest

sealed class MainStateEvent {

    class PostAuthEvent(val authRequest: AuthRequest) : MainStateEvent()

    class PutEditProfileEvent(val newProfile: EditProfileRequest) : MainStateEvent()

    object GetPostsEvent : MainStateEvent()

    object GetFollowersEvent : MainStateEvent()

    object GetProfileEvent : MainStateEvent()

    object GetAllLoremImagesEvent : MainStateEvent()

    class PostAddPicture(val idProfile: String, val postRequest: AddPostRequest) : MainStateEvent()

    object GetNumberPost : MainStateEvent()

    class GetLoremImageEvent(val id: String, val width: String, val height: String)
        : MainStateEvent()
}