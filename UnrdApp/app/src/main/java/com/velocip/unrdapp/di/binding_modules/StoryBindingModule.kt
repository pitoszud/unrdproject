package com.velocip.unrdapp.di.binding_modules

import com.velocip.unrdapp.data.source.StoryDataSource
import com.velocip.unrdapp.data.source.StoryLocalDataSource
import com.velocip.unrdapp.data.source.StoryRemoteDataSource
import com.velocip.unrdapp.repository.DefaultResultRepository
import com.velocip.unrdapp.repository.StoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StoryBindingModule {

    @StoryLocalDataImpl
    @Singleton
    @Binds
    abstract fun storyBindLocalDataSource(
        storyLocalDataSouce: StoryLocalDataSource
    ): StoryDataSource


    @StoryRemoteDataImpl
    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(
        storyRemoteDataSouce: StoryRemoteDataSource
    ): StoryDataSource



    @DefStoryRepoImpl
    // @ActivityScoped
    @Singleton
    @Binds
    abstract fun bindDefaultStoryRepository(
        defaultStoryRepository: DefaultResultRepository
    ): StoryRepository
    
}



@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StoryLocalDataImpl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class StoryRemoteDataImpl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefStoryRepoImpl