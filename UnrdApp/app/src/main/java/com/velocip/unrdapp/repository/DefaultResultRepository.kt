package com.velocip.unrdapp.repository

import androidx.lifecycle.LiveData
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.data.source.StoryDataSource
import com.velocip.unrdapp.utils.Result
import com.velocip.unrdapp.utils.Result.*
import kotlinx.coroutines.*
import javax.inject.Inject

class DefaultResultRepository @Inject constructor(
    private val storyRemoteDataSource: StoryDataSource,
    private val storyLocalDataSource: StoryDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): StoryRepository {

    override fun observeStories(): LiveData<Result<List<Story>>> {
        return storyLocalDataSource.observeStories()
    }

    override suspend fun getStories(forceUpdate: Boolean): Result<List<Story>> {
        if (forceUpdate) {
            try {
                updateStoriesFromRemoteDataSource()
            } catch (ex: Exception) {
                return Error(ex)
            }
        }
        return storyLocalDataSource.getStories()
    }

    override suspend fun refreshStories() {
        updateStoriesFromRemoteDataSource()
    }

    override fun observeStory(storyId: String): LiveData<Result<Story>> {
        return storyLocalDataSource.observeStory(storyId)
    }

    override suspend fun getStory(storyId: String, forceUpdate: Boolean): Result<Story> {
        if (forceUpdate) {
            updateStoryFromRemoteDataSource(storyId)
        }
        return storyLocalDataSource.getStory(storyId)
    }

    override suspend fun refreshStory(storyId: String) {
        updateStoryFromRemoteDataSource(storyId)
    }

    override suspend fun saveStory(story: Story) {
        coroutineScope {
            launch { storyRemoteDataSource.saveStory(story) }
            launch { storyLocalDataSource.saveStory(story) }
        }
    }

    override suspend fun deleteAllStories() {
        withContext(ioDispatcher) {
            coroutineScope {
                launch { storyRemoteDataSource.deleteAllStories() }
                launch { storyLocalDataSource.deleteAllStories() }
            }
        }
    }

    override suspend fun deleteStory(storyId: String) {
        coroutineScope {
            launch { storyRemoteDataSource.deleteStory(storyId) }
            launch { storyLocalDataSource.deleteStory(storyId) }
        }
    }

    private suspend fun updateStoriesFromRemoteDataSource() {
        val remoteStories = storyRemoteDataSource.getStories()

        if (remoteStories is Success) {
            // Real apps might want to do a proper sync.
            storyLocalDataSource.deleteAllStories()
            remoteStories.data.forEach { story ->
                storyLocalDataSource.saveStory(story)
            }
        } else if (remoteStories is Error) {
            throw remoteStories.exception
        }
    }


    private suspend fun updateStoryFromRemoteDataSource(storyId: String) {
        //val remoteStory = storyRemoteDataSource.getStory(storyId)
        val remoteStory = storyRemoteDataSource.getStoryAsync(storyId)

        if (remoteStory is Success) {
            storyLocalDataSource.saveStory(remoteStory.data)
        }
    }
}