package com.example.instagrammo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagrammo.beans.followers.FollowerBean
import com.example.instagrammo.beans.posts.PostBean
import com.example.instagrammo.beans.profile.ProfileBean
import com.example.instagrammo.repository.MainRepositoryImpl
import com.example.instagrammo.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val mainRepository = MainRepositoryImpl.newInstance()

    private val _dataState: MutableLiveData<DataState<List<String>>> = MutableLiveData()

    private val _dataStateAuth: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    private val _dataStatePosts: MutableLiveData<DataState<List<PostBean>>> = MutableLiveData()

    private val _dataStateFollowers: MutableLiveData<DataState<List<FollowerBean>>> = MutableLiveData()

    private val _dataStateProfile: MutableLiveData<DataState<ProfileBean>> = MutableLiveData()

    private val _dataStateEditProfile: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    val dataStatePost: LiveData<DataState<List<PostBean>>>
        get() = _dataStatePosts

    val dataStateAuth : LiveData<DataState<Boolean>>
        get() = _dataStateAuth

    val dataStateFollowers : LiveData<DataState<List<FollowerBean>>>
        get() = _dataStateFollowers

    val dataStateProfile : LiveData<DataState<ProfileBean>>
        get() = _dataStateProfile

    val dataStateEditProfile : LiveData<DataState<Boolean>>
        get() = _dataStateEditProfile


    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {

                is MainStateEvent.PostAuthEvent -> {
                    mainRepository.postAuth(mainStateEvent.authRequest)
                        .onEach { dataStateAuth -> _dataStateAuth.value = dataStateAuth }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.GetPostsEvent -> {
                    mainRepository.getPosts()
                        .onEach { dataStatePosts -> _dataStatePosts.value = dataStatePosts}
                        .launchIn(viewModelScope)

                }

                is MainStateEvent.GetFollowersEvent -> {
                    mainRepository.getFollowers()
                        .onEach { dataStateFollowers -> _dataStateFollowers.value = dataStateFollowers}
                        .launchIn(viewModelScope)

                }

                is MainStateEvent.GetProfileEvent -> {
                    mainRepository.getProfile()
                        .onEach { dataStateProfile -> _dataStateProfile.value = dataStateProfile}
                        .launchIn(viewModelScope)

                }

                is MainStateEvent.PutEditProfileEvent-> {
                    mainRepository.putProfile(mainStateEvent.newProfile)
                        .onEach { dataStateEditProfile -> _dataStateEditProfile.value = dataStateEditProfile}
                        .launchIn(viewModelScope)

                }
            }
        }
    }


}