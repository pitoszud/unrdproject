package com.velocip.unrdapp.di.repository_modules

import com.velocip.unrdapp.data.source.StoryDataSource
import com.velocip.unrdapp.di.binding_modules.StoryLocalDataImpl
import com.velocip.unrdapp.di.binding_modules.StoryRemoteDataImpl
import com.velocip.unrdapp.repository.DefaultResultRepository
import com.velocip.unrdapp.repository.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Singleton
    @Provides
    fun provideDefaultRepository(
        @StoryRemoteDataImpl storyRemoteDataSouce: StoryDataSource,
        @StoryLocalDataImpl storyLocalDataSouce: StoryDataSource
    ): StoryRepository {
        return DefaultResultRepository(storyRemoteDataSouce, storyLocalDataSouce)
    }

}