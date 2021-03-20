package com.velocip.unrdapp.di.database_modules

import android.content.Context
import com.velocip.unrdapp.persistance.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getInstance(appContext)


    @Singleton
    @Provides
    fun provideStoryResult(db: AppDatabase) = db.storyResultDao()
}