package com.example.instagrammo.environment.networking

import com.example.instagrammo.prefs
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object ApiClient {

    const val BASEURL : String = "http://www.nbarresi.it/"
    //const val BASEURL : String = "http://192.168.1.10:3001/Instagrammo/"
    const val BASEURLLOREM : String = "https://picsum.photos/"

    private var url: String = BASEURL


    val GetClient : ApiInterface
    get(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = BODY

        val client: OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest: Request = chain.request().newBuilder()
                        .addHeader("x-api-key", prefs.rememberToken!!)
                        .build()
                    return chain.proceed(newRequest)
                }

            }).build()
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    fun setBaseUrl(baseUrl : Boolean = true) : ApiInterface{
        url = if (baseUrl) BASEURL else BASEURLLOREM
        return GetClient
    }

}