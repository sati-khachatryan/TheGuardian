package com.example.theguardianapp.repo.remote

import com.example.theguardianapp.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getData(
        @Query("show-fields") showFields: String = "thumbnail,body-text",
        @Query("page") page: Int,
        @Query("page-size") pageCount: Int
    ): ResponseData

    @GET("search")
    suspend fun searchData(
        @Query("show-fields") showFields: String = "thumbnail,body-text",
        @Query("q") query: String,
        @Query("page-size") pageCount: Int
    ): ResponseData
}