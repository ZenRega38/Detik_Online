package com.regadeveloper.detik.service

import com.regadeveloper.detik.model.ResponseNews
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

object RetrofitBuilder {

    private val client: OkHttpClient = OkHttpClient.Builder().build()

    private val retrofit: Retrofit = Retrofit.Builder() //konfigurasi url dasar
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getService() = retrofit.create(TopHeadLines::class.java)
}

    interface TopHeadLines{
        @Headers("Authorization: e0b34d0277384283943382798c742dea")
        @GET("v2/top-headlines?country=id")

        fun fechHeadLines(): Call<ResponseNews>
    }