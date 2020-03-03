package com.bolaware.architectureexample.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieRetrofitFactory {
    private const val BASE_URL = "https://www.omdbapi.com/"
    const val OMDB_API_KEY = "379b7593"
    const val MOVIE_TYPE = "movie"

    private val okHttpClient by lazy {
        providesOkHttpClient()
    }

    private val retrofit by lazy {
        providesRetrofit(okHttpClient)
    }

    val service by lazy {
        providesApiService(retrofit)
    }

    private fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {

        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .connectTimeout(3, TimeUnit.MINUTES)
            .apply {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addNetworkInterceptor(logging)

            }
            .build()
    }
}