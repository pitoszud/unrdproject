package com.velocip.unrdapp.service

import com.velocip.unrdapp.data.models.StoryResult
import retrofit2.Call
import retrofit2.http.GET

interface Story2Api {

    @GET("https://s3-eu-west-1.amazonaws.com/unrd-scratch/resp.json")
    fun getResult(): Call<StoryResult>
}