package com.example.instagrammo.repository

import android.provider.ContactsContract
import com.example.instagrammo.beans.auth.AuthRequest
import com.example.instagrammo.beans.followers.FollowerBean
import com.example.instagrammo.beans.posts.PostBean
import com.example.instagrammo.beans.profile.EditProfileRequest
import com.example.instagrammo.beans.profile.ProfileBean
import com.example.instagrammo.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun postAuth(authRequest: AuthRequest) : Flow<DataState<Boolean>>

    fun getPosts() : Flow<DataState<List<PostBean>>>

    fun getFollowers() : Flow<DataState<List<FollowerBean>>>

    fun getProfile() : Flow<DataState<ProfileBean>>

    fun putProfile(data: EditProfileRequest) : Flow<DataState<Boolean>>
}