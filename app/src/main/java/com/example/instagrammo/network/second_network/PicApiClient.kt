package com.example.instagrammo.network.second_network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PicApiClient {
    const val BASE_URL: String = "https://picsum.photos/v2/list/"

    val getClient: PicApiInterface
        get() {

            val interceptor = HttpLoggingInterceptor()
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val gson = GsonBuilder()
                .setLenient()
                .create()


            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(PicApiInterface::class.java)

        }
}