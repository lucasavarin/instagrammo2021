package utils.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.extensions.prefs

object ApiClient {
    const val BASE_URL: String = "http://www.nbarresi.it/"

    val getClient: ApiInterface
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val req =
                        chain.request().newBuilder().addHeader("x-api-key", prefs.authToken).build()
                    return chain.proceed(req)
                }
            }).build()

            val gson = GsonBuilder().create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
}