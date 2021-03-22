package com.velocip.unrdapp

import androidx.lifecycle.LiveData
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.data.models.StoryResult
import com.velocip.unrdapp.data.source.StoryDataSource
import com.velocip.unrdapp.utils.Result
import com.velocip.unrdapp.utils.Result.*
import java.lang.Exception

class FakeDataSource(private val stories: MutableList<Story>? = mutableListOf()): StoryDataSource {
    override fun observeStories(): LiveData<Result<List<Story>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getStories(): Result<List<Story>> {
        stories?.let {
            return Success(ArrayList(it))
        }
        return Error(Exception("Result not found"))
    }

    override suspend fun refreshStories() {
        TODO("Not yet implemented")
    }

    override fun observeStory(storyId: String): LiveData<Result<Story>> {
        TODO("Not yet implemented")
    }

    override suspend fun getStory(storyId: String): Result<Story> {
        TODO("Not yet implemented")
    }

    override suspend fun getStoryAsync(storyId: String): Result<Story> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshStory(storyId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveStory(story: Story) {
        stories?.add(story)
    }

    override suspend fun deleteAllStories() {
        stories?.clear()
    }

    override suspend fun deleteStory(storyId: String) {

    }
}