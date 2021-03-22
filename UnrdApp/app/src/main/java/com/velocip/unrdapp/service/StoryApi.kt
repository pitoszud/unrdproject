package com.velocip.unrdapp.service

import com.velocip.unrdapp.data.models.StoryResult
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StoryApi {

    @GET("story")
    suspend fun getStory(
        @Query("query") query: String
    ) : Response<StoryResult>?


    @GET("unrd-scratch/resp.json")
    suspend fun getStoryResult() : Response<StoryResult>?

    @GET("unrd-scratch/resp.json")
    suspend fun getStoryResultAsync() : StoryResult
}