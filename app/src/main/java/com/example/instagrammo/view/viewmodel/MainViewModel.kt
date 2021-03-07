package com.example.instagrammo.view.viewmodel

import androidx.lifecycle.*
import com.example.instagrammo.beans.business.followers.FollowerBean
import com.example.instagrammo.beans.business.lorem.LoremBean
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.beans.business.post.PostProfileBean
import com.example.instagrammo.beans.business.profile.ProfileBean
import com.example.instagrammo.beans.rest.lorem.LoremRest
import com.example.instagrammo.beans.rest.post.NumberPosts
import com.example.instagrammo.environment.repository.MainRepositoryImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class MainViewModel() : ViewModel() {

    private val mainRepository = MainRepositoryImpl.newInstance()

    private val _dataStateAuth: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    private val _dataStatePosts: MutableLiveData<DataState<List<PostBean>>> = MutableLiveData()

    private val _dataStatePostsProfile: MutableLiveData<DataState<List<PostProfileBean>>> = MutableLiveData()

    private val _dataStateFollowers: MutableLiveData<DataState<List<FollowerBean>>> = MutableLiveData()

    private val _dataStateProfile: MutableLiveData<DataState<ProfileBean>> = MutableLiveData()

    private val _dataStateEditProfile: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    private val _dataStateAllLoremImages: MutableLiveData<DataState<List<LoremRest>>> = MutableLiveData()

    private val _dataStateLoremImage: MutableLiveData<DataState<LoremBean>> = MutableLiveData()

    private val _dataStateAddPost: MutableLiveData<DataState<Boolean>> = MutableLiveData()

    private val _dataStateNumberPosts: MutableLiveData<DataState<NumberPosts>> = MutableLiveData()

    private val _dataStateImage: MutableLiveData<DataState<ResponseBody>> = MutableLiveData()

    val dataStatePost: LiveData<DataState<List<PostBean>>>
        get() = _dataStatePosts

    val dataStatePostProfile: LiveData<DataState<List<PostProfileBean>>>
        get() = _dataStatePostsProfile

    val dataStateAuth : LiveData<DataState<Boolean>>
        get() = _dataStateAuth

    val dataStateFollowers : LiveData<DataState<List<FollowerBean>>>
        get() = _dataStateFollowers

    val dataStateProfile : LiveData<DataState<ProfileBean>>
        get() = _dataStateProfile

    val dataStateEditProfile : LiveData<DataState<Boolean>>
        get() = _dataStateEditProfile

    val dataStateAllLoremImages : LiveData<DataState<List<LoremRest>>>
        get() = _dataStateAllLoremImages

    val dataStateLoremImage : LiveData<DataState<LoremBean>>
        get() = _dataStateLoremImage

    val dataStateAddPost : LiveData<DataState<Boolean>>
        get() = _dataStateAddPost

    val dataStateNumberPosts : LiveData<DataState<NumberPosts>>
        get() = _dataStateNumberPosts

    val dataStateImage : LiveData<DataState<ResponseBody>>
        get() = _dataStateImage


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

                is MainStateEvent.GetAllLoremImagesEvent -> {
                    mainRepository.getListPictureLorem()
                        .onEach {  dataStateLoremImage -> _dataStateAllLoremImages.value = dataStateLoremImage }
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.PostAddPicture -> {
                    mainRepository.postAddPost(mainStateEvent.idProfile, mainStateEvent.postRequest)
                        .onEach { dataStateAddPost -> _dataStateAddPost.value = dataStateAddPost}
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.GetNumberPost -> {
                    mainRepository.getNumberPost()
                        .onEach { dataStateNumberPosts -> _dataStateNumberPosts.value = dataStateNumberPosts}
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.GetPostsProfileEvent -> {
                    mainRepository.getPostsProfile()
                        .onEach { dataStatePostProfile -> _dataStatePostsProfile.value = dataStatePostProfile}
                        .launchIn(viewModelScope)
                }

                is MainStateEvent.GetImageEvent -> {
                    mainRepository.getImage(mainStateEvent.url)
                        .onEach { dataStateImage -> _dataStateImage.value = dataStateImage}
                        .launchIn(viewModelScope)
                }


/*
                is MainStateEvent.GetLoremImageEvent -> {
                    mainRepository.getPictureLorem(mainStateEvent.id, mainStateEvent.width, mainStateEvent.height)
                        .onEach {  dataStateLoremImage -> _dataStateLoremImage.value = dataStateLoremImage }
                        .launchIn(viewModelScope)
                }*/
            }
        }
    }

    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }

}