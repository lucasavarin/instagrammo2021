package com.example.instagrammo.repository

import com.example.instagrammo.beans.auth.AuthRequest
import com.example.instagrammo.beans.followers.FollowerBean
import com.example.instagrammo.beans.posts.PostBean
import com.example.instagrammo.beans.profile.EditProfileRequest
import com.example.instagrammo.beans.profile.ProfileBean
import com.example.instagrammo.prefs
import com.example.instagrammo.retrofit.ApiClient
import com.example.instagrammo.retrofit.ApiInterface
import com.example.instagrammo.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainRepositoryImpl(): MainRepository {

    companion object {
        fun newInstance(): MainRepositoryImpl = MainRepositoryImpl()
    }

    private val apiService = ApiClient.GetClient

    override fun postAuth(authRequest: AuthRequest): Flow<DataState<Boolean>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = apiService.doAuth(authRequest)
                val responseExcuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExcuted.isSuccessful)
                    if (responseExcuted.body()?.result != null && responseExcuted.body()!!.result == true) {
                        prefs.rememberToken = responseExcuted.body()?.authToken.toString()
                        prefs.rememberIdProfile = responseExcuted.body()?.profileId.toString()
                        emit(DataState.Success(responseExcuted.body()?.result!!))
                    }
            } catch (e: Exception) {
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
                    if (responseExcuted.body()?.result != null && responseExcuted.body()?.result!!) {
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
                val response = apiService.getFollowers(prefs.rememberIdProfile)
                val responseExcuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExcuted.isSuccessful)
                    if (responseExcuted.body()?.result != null && responseExcuted.body()?.result!!) {
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
                val response = apiService.getProfile(prefs.rememberIdProfile)
                val responseExcuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExcuted.isSuccessful)
                    if (responseExcuted.body()?.payload != null && responseExcuted.body()?.result!!) {
                        val data = responseExcuted.body()!!.payload!!.map { follower -> ProfileBean.convert(follower) }[0]
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
                val responseExcuted = withContext(Dispatchers.IO) { response.execute() }

                if (responseExcuted.isSuccessful)
                    if (responseExcuted.body()?.result != null)
                        emit(DataState.Success(responseExcuted.body()?.result!!))

            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
    }


}