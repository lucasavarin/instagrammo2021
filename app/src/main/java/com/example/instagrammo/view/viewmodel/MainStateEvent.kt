package com.example.instagrammo.view.viewmodel

import com.example.instagrammo.beans.rest.auth.AuthRequest
import com.example.instagrammo.beans.rest.profile.edit.EditProfileRequest

sealed class MainStateEvent {

    class PostAuthEvent(val authRequest: AuthRequest) : MainStateEvent()

    class PutEditProfileEvent(val newProfile: EditProfileRequest) : MainStateEvent()

    object GetPostsEvent : MainStateEvent()

    object GetFollowersEvent : MainStateEvent()

    object GetProfileEvent : MainStateEvent()
}