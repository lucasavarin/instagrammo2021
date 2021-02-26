package com.example.instagrammo.environment.repository

import com.example.instagrammo.beans.rest.auth.AuthRequest
import com.example.instagrammo.beans.business.followers.FollowerBean
import com.example.instagrammo.beans.business.post.PostBean
import com.example.instagrammo.beans.rest.profile.edit.EditProfileRequest
import com.example.instagrammo.beans.business.profile.ProfileBean
import com.example.instagrammo.beans.rest.lorem.LoremRest
import com.example.instagrammo.beans.rest.post.AddPostRequest
import com.example.instagrammo.prefs
import com.example.instagrammo.environment.networking.ApiClient
import com.example.instagrammo.environment.networking.ApiClientLorem
import com.example.instagrammo.view.viewmodel.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception

class MainRepositoryImpl():
    MainRepository {

    companion object {
        fun newInstance(): MainRepositoryImpl =
            MainRepositoryImpl()
    }

    private val apiService = ApiClient.GetClient

    override fun postAuth(authRequest: AuthRequest): Flow<DataState<Boolean>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = apiService.doAuth(authRequest)
                val responseExcuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExcuted.isSuccessful)
                    if (responseExcuted.body()?.result != null) {
                        prefs.token = if (responseExcuted.body()?.authToken != null) responseExcuted.body()?.authToken.toString() else ""
                        prefs.idProfile = if (responseExcuted.body()?.profileId != null) responseExcuted.body()?.profileId.toString() else ""
                        emit(DataState.Success(responseExcuted.body()?.result!!))
                    }
            } catch (e: IOException) {
                emit(DataState.Error(e))
            }

        }
    }

    override fun getPosts() : Flow<DataState<List<PostBean>>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = apiService.getPosts()
                val responseExcuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExcuted.isSuccessful)
                    if (responseExcuted.body()?.result != null) {
                        val data = responseExcuted.body()!!.payload!!.map { post -> PostBean.convert(post) }
                        emit(DataState.Success(data))
                    }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }


    override fun getFollowers() : Flow<DataState<List<FollowerBean>>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = apiService.getFollowers(prefs.idProfile)
                val responseExcuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExcuted.isSuccessful)
                    if (responseExcuted.body()?.result != null) {
                        val data = responseExcuted.body()!!.payload!!.map { follower -> FollowerBean.convert(follower) }
                        emit(DataState.Success(data))
                    }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override fun getProfile(): Flow<DataState<ProfileBean>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = apiService.getProfile(prefs.idProfile)
                val responseExecuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExecuted.isSuccessful)
                    if (responseExecuted.body()?.payload != null) {
                        val data = responseExecuted.body()!!.payload!!.map { follower -> ProfileBean.convert(follower) }[0]
                        emit(DataState.Success(data))
                    }
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override fun putProfile(newData: EditProfileRequest): Flow<DataState<Boolean>> {
        return flow{
            emit(DataState.Loading)
            try {
                val response = apiService.putProfile(newData)
                val responseExecuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExecuted.isSuccessful)
                    if (responseExecuted.body()?.result != null)
                        emit(DataState.Success(responseExecuted.body()?.result!!))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override fun getListPictureLorem(): Flow<DataState<List<LoremRest>>> {
        return flow {
            emit(DataState.Loading)
            try {

                val response = ApiClientLorem.GetClient.getAllLoremPictures()
                val responseExecuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExecuted.isSuccessful){
                    val data = responseExecuted.body()!!
                    emit(DataState.Success(data))
                }
                ApiClient.setBaseUrl()
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }

    override fun postAddPost(idProfile: String, post: AddPostRequest): Flow<DataState<Boolean>> {
        return flow {
            emit(DataState.Loading)
            try {

                val response = ApiClient.GetClient.postAddPost(idProfile, post)
                val responseExecuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExecuted.isSuccessful)
                    emit(DataState.Success(responseExecuted.body()!!.result!!))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }
/*
    override fun getPictureLorem(id: String, width: String, height: String): Flow<DataState<LoremBean>> {
        return flow {
            emit(DataState.Loading)
            try {

                val response = ApiClientLorem.GetClient.getLoremPicture(id, width, height)
                val responseExecuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExecuted.isSuccessful){
                    val data = responseExecuted.body()!!
                    val dataBean = LoremBean.convert(data)
                    emit(DataState.Success(dataBean))
                    Log.i("ciao", dataBean.download_url)
                }
                ApiClient.setBaseUrl()
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }



 */
}