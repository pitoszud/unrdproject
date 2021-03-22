package com.velocip.unrdapp.di.service_modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.velocip.unrdapp.service.StoryApi
import com.velocip.unrdapp.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
@ExperimentalSerializationApi
object StoryApiModule {

    private val reqInterceptor = Interceptor { chain ->

        val url = chain.request()
            .url
            .newBuilder()
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)
    }


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(reqInterceptor)
        .connectTimeout(AppConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(AppConstants.READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(AppConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build()


    val contentType = "application/json".toMediaType()
    private var moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(AppConstants.STORY_BASE_URL)
        .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))


    //.addConverterFactory(Json.asConverterFactory(contentType))




    @Singleton
    @Provides
    fun providesStoryApi(): StoryApi{
        return retrofitBuilder
            .build()
            .create(StoryApi::class.java)
    }

}