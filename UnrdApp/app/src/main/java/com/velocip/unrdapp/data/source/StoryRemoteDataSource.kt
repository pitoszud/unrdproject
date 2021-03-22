package com.velocip.unrdapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.data.models.StoryResult
import com.velocip.unrdapp.data.models.toStory
import com.velocip.unrdapp.service.StoryApi
import com.velocip.unrdapp.utils.Result
import com.velocip.unrdapp.utils.Result.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class StoryRemoteDataSource(
    private val storyApi: StoryApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): StoryDataSource {

    private val observableStories = MutableLiveData<Result<List<Story>>>()
    
    override fun observeStories(): LiveData<Result<List<Story>>> {
        return observableStories
    }

    override suspend fun getStories(): Result<List<Story>> {
        val storyRes: StoryResult = storyApi.getStoryResultAsync()
        val story = storyRes.toStory()
        return Success(listOf(story))
    }

    override suspend fun refreshStories() {
        observableStories.value = getStories()!!
    }

    override fun observeStory(storyId: String): LiveData<Result<Story>> {
        return observableStories.map { stories ->
            when (stories) {
                is Loading -> Loading
                is Error -> Error(stories.exception)
                is Success -> {
                    val story = stories.data.firstOrNull() { it.storyId == storyId }
                        ?: return@map Error(Exception("Story Not found"))
                    Success(story)
                }
            }
        }
    }

    override suspend fun getStory(storyId: String): Result<Story> = withContext(ioDispatcher){
        try {
            val result: Response<StoryResult> = storyApi.getStoryResult()
                ?: return@withContext Error(java.lang.Exception("Unknown error occured"))

            if(result.isSuccessful){
                return@withContext result.body()?.let {
                    val story = it.toStory()
                    return@let Success(story)
                } ?: Error(java.lang.Exception("Story is null"))
            }else{
                return@withContext Error(java.lang.Exception("Unknown error occured"))
            }
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }


    override suspend fun getStoryAsync(storyId: String): Result<Story> = withContext(ioDispatcher){
        try {
            val result = storyApi.getStoryResultAsync()
            return@withContext Success(result.toStory())
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun refreshStory(storyId: String) {
        refreshStories()
    }

    override suspend fun saveStory(story: Story) {
        // n/a
    }

    override suspend fun deleteAllStories() {
        // n/a
    }

    override suspend fun deleteStory(storyId: String) {
        // n/a
    }
}