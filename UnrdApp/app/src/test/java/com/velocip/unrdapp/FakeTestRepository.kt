package com.velocip.unrdapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.repository.StoryRepository
import com.velocip.unrdapp.utils.Result
import com.velocip.unrdapp.utils.Result.*
import kotlinx.coroutines.runBlocking

class FakeTestRepository: StoryRepository {
    private val storyApi: LinkedHashMap<String, Story> = LinkedHashMap()
    private var shouldReturnError = false
    private val observableStories = MutableLiveData<Result<List<Story>>>()

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override fun observeStories(): LiveData<Result<List<Story>>> {
        runBlocking {
            refreshStories()
        }
        return observableStories
    }

    override suspend fun getStories(forceUpdate: Boolean): Result<List<Story>> {
        if (shouldReturnError){
            return Error(java.lang.Exception("Test exception"))
        }
        return Success(storyApi.values.toList())
    }

    override suspend fun refreshStories() {
        observableStories.value = getStories()
    }

    override fun observeStory(storyId: String): LiveData<Result<Story>> {
        runBlocking { refreshStories() }
        return observableStories.map { stories ->
            when (stories) {
                is Loading -> Loading
                is Error -> Error(Exception("Error"))
                is Success -> {
                    val story = stories.data.firstOrNull() { it.storyId == storyId }
                        ?: return@map Error(Exception("Not found"))
                    Success(story)
                }
            }
        }
    }

    override suspend fun getStory(storyId: String, forceUpdate: Boolean): Result<Story> {
        if (shouldReturnError){
            return Error(java.lang.Exception("Test exception"))
        }
        storyApi[storyId]?.let {
            return Success(it)
        }
        return Error(java.lang.Exception("Story not found"))
    }

    override suspend fun refreshStory(storyId: String) {
        refreshStories()
    }

    override suspend fun saveStory(story: Story) {
        storyApi[story.storyId] = story
    }

    override suspend fun deleteAllStories(){
        storyApi.clear()
        refreshStories()
    }

    override suspend fun deleteStory(storyId: String) {
        storyApi.remove(storyId)
        refreshStories()
    }

    fun initStories(vararg stories: Story){
        for(story in stories){
            storyApi[story.storyId] = story
        }
        runBlocking { refreshStories() }
    }
}