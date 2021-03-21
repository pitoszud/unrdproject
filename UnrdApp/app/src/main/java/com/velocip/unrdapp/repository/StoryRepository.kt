package com.velocip.unrdapp.repository

import androidx.lifecycle.LiveData
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.utils.Result

interface StoryRepository {

    fun observeStories(): LiveData<Result<List<Story>>>

    suspend fun getStories(forceUpdate: Boolean = false): Result<List<Story>>

    suspend fun refreshStories()

    fun observeStory(storyId: String): LiveData<Result<Story>>

    suspend fun getStory(storyId: String, forceUpdate: Boolean = false): Result<Story>

    suspend fun refreshStory(storyId: String)

    suspend fun saveStory(story: Story)

    suspend fun deleteAllStories()

    suspend fun deleteStory(storyId: String)
}