package com.mbds.newsletter.data.interfaces

import com.mbds.newsletter.data.models.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    @GET("everything")
    fun list(@Query("q") category: String): Call<ArticlesResponse>
}