package com.costa.servizi

import com.costa.`interface`.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val BASE_URL="http://www.nbarresi.it/"
    val getClient: ApiInterface
        get(){
            val interceptor= HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            val client =OkHttpClient.Builder().addInterceptor(interceptor).build()

            val gson= GsonBuilder().create()
            val retrofit= Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(ApiInterface::class.java)

    }
}