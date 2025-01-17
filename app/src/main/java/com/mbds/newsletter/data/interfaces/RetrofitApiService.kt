package com.mbds.newsletter.data.interfaces

import com.mbds.newsletter.data.models.ArticlesResponse
import com.mbds.newsletter.data.models.EditorResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    @GET("everything")
    fun list(@Query("q") category: String): Call<ArticlesResponse>

    @GET("sources")
    fun listEditors(): Call<EditorResponse>

    @GET("everything")
    fun listArticlesFiltered(@Query("q") category: String, @Query("sources") sources: String, @Query("language") country: String): Call<ArticlesResponse>
}