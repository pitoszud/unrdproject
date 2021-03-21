package com.velocip.unrdapp.service

import com.velocip.unrdapp.data.StoryDto
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


    @GET("story_result")
    suspend fun getStoryResult() : Response<StoryResult>?

    @GET("story_result")
    suspend fun getStoryResultAsync() : Deferred<StoryResult>
}