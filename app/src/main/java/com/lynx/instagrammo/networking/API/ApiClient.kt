package com.lynx.instagrammo.networking.API

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object ApiClient {

    const val BASE_URL: String = "http://www.nbarresi.it/"
    const val BASE_PICSUM_URL: String = "https://picsum.photos/"

    var authToken: String = ""

    val GetClient: ApiInterface
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(object : Interceptor {
                        @Throws(IOException::class)
                        override fun intercept(chain: Interceptor.Chain): Response {
                            val newRequest: Request = chain.request().newBuilder()
                                    .addHeader("x-api-key", authToken)
                                    .build()
                            return chain.proceed(newRequest)
                        }
                    }).build()

            val gson = GsonBuilder().create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create(ApiInterface::class.java)

        }

    val GetPicsumClient: ApiPicsumInterface
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            val gson = GsonBuilder().create()

            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_PICSUM_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            return retrofit.create(ApiPicsumInterface::class.java)
        }

}






