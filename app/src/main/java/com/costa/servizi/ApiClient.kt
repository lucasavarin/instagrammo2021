package com.costa.servizi

import com.costa.`interface`.ApiImageInterface
import com.costa.`interface`.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    var key:String="x-api-key"
    var authToken:String = ""
    var userId:String = ""
    val BASE_URL = "http://www.nbarresi.it/"
    val IMAGE_URL="https://picsum.photos/"
    var client:OkHttpClient?=null
    val getClient: ApiInterface
            get() {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val httClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(object : Interceptor {
                        override fun intercept(chain: Interceptor.Chain): Response {
                            val req =
                                chain.request().newBuilder().addHeader("x-api-key", authToken).build()
                            return chain.proceed(req)
                        }
                    }).build()
            val gson = GsonBuilder().create()
            val retrofit = Builder()
                    .baseUrl(BASE_URL)
                    .client(httClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retrofit.create(ApiInterface::class.java)

        }
    val getImageClient: ApiImageInterface
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httClient = OkHttpClient.Builder()
                .addInterceptor(logging).build()

            val gson = GsonBuilder().create()
            val retrofit = Builder()
                .baseUrl(IMAGE_URL)
                .client(httClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(ApiImageInterface::class.java)

        }
}