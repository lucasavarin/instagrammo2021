package com.example.instagrammo.retrofit

import com.example.instagrammo.prefs
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASEURL : String = "http://www.nbarresi.it/"
    //const val BASEURL : String = "http://192.168.1.10:3001/Instagrammo/"

    val TOKEN : String? = prefs.rememberToken
    val PROFILE_ID : String? = prefs.rememberIdProfile

    val GetClient : ApiInterface
    get(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(ApiInterface::class.java)

    }
}