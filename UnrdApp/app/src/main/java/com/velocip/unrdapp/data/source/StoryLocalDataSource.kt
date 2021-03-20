package com.velocip.unrdapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.velocip.unrdapp.data.Story
import com.velocip.unrdapp.persistance.StoryDao
import com.velocip.unrdapp.utils.Result
import com.velocip.unrdapp.utils.Result.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StoryLocalDataSource(
    private val storyDao: StoryDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): StoryDataSource {

    override fun observeStories(): LiveData<Result<List<Story>>> {
        return storyDao.observeStories().map {
            Success(it)
        }
    }

    override suspend fun getStories(): Result<List<Story>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(storyDao.getStories())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun refreshStories() {
        // n/a
    }

    override fun observeStory(storyId: String): LiveData<Result<Story>> {
        return storyDao.observeStoryById(storyId).map {
            Success(it)
        }
    }

    override suspend fun getStory(storyId: String): Result<Story> = withContext(ioDispatcher) {
        try {
            val story = storyDao.getStoryById(storyId)
            if (story != null) {
                return@withContext Success(story)
            } else {
                return@withContext Error(Exception("Story not found!"))
            }
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun refreshStory(storyId: String) {
        // n/a
    }

    override suspend fun saveStory(story: Story) = withContext(ioDispatcher) {
        storyDao.insertStory(story)
    }

    override suspend fun deleteAllStories() = withContext(ioDispatcher) {
        storyDao.deleteStories()
    }

    override suspend fun deleteStory(storyId: String) = withContext<Unit>(ioDispatcher) {
        storyDao.deleteStoryById(storyId)
    }


}