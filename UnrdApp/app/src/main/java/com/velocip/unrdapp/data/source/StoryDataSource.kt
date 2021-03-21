package com.velocip.unrdapp.data.source

import androidx.lifecycle.LiveData
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.utils.Result

interface StoryDataSource{

    fun observeStories(): LiveData<Result<List<Story>>>

    suspend fun getStories(): Result<List<Story>>

    suspend fun refreshStories()

    fun observeStory(storyId: String): LiveData<Result<Story>>

    suspend fun getStory(storyId: String): Result<Story>

    suspend fun getStoryAsync(storyId: String): Result<Story>

    suspend fun refreshStory(storyId: String)

    suspend fun saveStory(story: Story)

    suspend fun deleteAllStories()

    suspend fun deleteStory(storyId: String)
}