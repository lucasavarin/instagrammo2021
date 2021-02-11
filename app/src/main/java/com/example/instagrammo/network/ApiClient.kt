package com.example.instagrammo.network

import com.example.instagrammo.view.prefs
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL: String = "http://www.nbarresi.it/"

    val getClient: ApiInterface
        get() {
            val httClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            httClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val req =
                        chain.request().newBuilder().addHeader("x-api-key", prefs.token).build()
                    return chain.proceed(req)
                }
            })
            logging.level = HttpLoggingInterceptor.Level.BODY
            httClient.addInterceptor(logging)

            val gson = GsonBuilder().create()


            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)

        }
}