package com.lynx.instagrammo.API

import android.provider.Telephony.TextBasedSmsColumns.BODY
import com.google.gson.GsonBuilder
import com.lynx.instagrammo.API.ApiClient.BASE_URL
import com.lynx.instagrammo.prefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import kotlin.jvm.Throws

object ApiClient {


    const val BASE_URL: String = "http://www.nbarresi.it/"

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
                            .addHeader("x-api-key", prefs.authToken!!)
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

}


//    private val client = OkHttpClient.Builder().apply {
//        addInterceptor(HttpLoggingInterceptor())
//    }.build()
//
//    private val retrofit by lazy {
//        Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//                .build()
//    }
//
//    val api: ApiInterface by lazy {
//        retrofit.create(ApiInterface::class.java)
//    }





