package com.mbds.newsletter.data.interfaces

import com.mbds.newsletter.data.models.Article
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApiService {
    @GET("/everything")
    fun list(): Call<List<Article>>
}