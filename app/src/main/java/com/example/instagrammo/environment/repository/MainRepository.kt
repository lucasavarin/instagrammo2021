package com.example.instagrammo.environment.repository

import com.example.instagrammo.beans.rest.auth.AuthRequest
import com.example.instagrammo.beans.business.followers.FollowerBean
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.beans.rest.profile.edit.EditProfileRequest
import com.example.instagrammo.beans.business.profile.ProfileBean
import com.example.instagrammo.view.viewmodel.DataState
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun postAuth(authRequest: AuthRequest) : Flow<DataState<Boolean>>

    fun getPosts() : Flow<DataState<List<PostBean>>>

    fun getFollowers() : Flow<DataState<List<FollowerBean>>>

    fun getProfile() : Flow<DataState<ProfileBean>>

    fun putProfile(data: EditProfileRequest) : Flow<DataState<Boolean>>
}