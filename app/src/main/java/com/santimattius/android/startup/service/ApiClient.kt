package com.santimattius.android.startup.service

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.File

object ApiClient {

    lateinit var retrofit: Retrofit

    fun initialize(applicationContext: Context, baseUrl: String) {
        // init api client using retrofit and define custom cache for example.
        val httpCacheDirectory: File = File(applicationContext.cacheDir, "http-cache")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB

        val cache = Cache(httpCacheDirectory, cacheSize.toLong())
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    inline fun <reified T : Any> create() = retrofit.create<T>()
}