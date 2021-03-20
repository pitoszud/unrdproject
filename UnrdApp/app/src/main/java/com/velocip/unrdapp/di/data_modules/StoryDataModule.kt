package com.velocip.unrdapp.di.data_modules

import com.velocip.unrdapp.data.source.StoryLocalDataSource
import com.velocip.unrdapp.data.source.StoryRemoteDataSource
import com.velocip.unrdapp.persistance.StoryDao
import com.velocip.unrdapp.service.StoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StoryDataModule {

    @Singleton
    @Provides
    fun provideStoryLocalDataSource(
        storyDao: StoryDao
    ): StoryLocalDataSource {
        return StoryLocalDataSource(storyDao)
    }


    @Singleton
    @Provides
    fun provideStoryRemoteDataSource(
        storyApi: StoryApi
    ): StoryRemoteDataSource {
        return StoryRemoteDataSource(storyApi)
    }
}