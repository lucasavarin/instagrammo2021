package com.example.instagrammo.viewmodel

import com.example.instagrammo.beans.auth.AuthRequest
import com.example.instagrammo.beans.profile.EditProfileRequest

sealed class MainStateEvent {

    class PostAuthEvent(val authRequest: AuthRequest) : MainStateEvent()

    class PutEditProfileEvent(val newProfile: EditProfileRequest) : MainStateEvent()

    object GetPostsEvent : MainStateEvent()

    object GetFollowersEvent : MainStateEvent()

    object GetProfileEvent : MainStateEvent()
}