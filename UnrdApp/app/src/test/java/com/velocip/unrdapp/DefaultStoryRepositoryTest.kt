package com.velocip.unrdapp

import com.google.common.truth.Truth.assertThat
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.data.models.StoryResult
import com.velocip.unrdapp.repository.DefaultResultRepository
import com.velocip.unrdapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultStoryRepositoryTest {
    private val storyResult = Story(
        storyId = "1234",
        name = "Fake Story"
    )

    private val remoteStories = listOf(storyResult)
    private val localStories = listOf(storyResult)

    private lateinit var storyRemoteDataSource: FakeDataSource
    private lateinit var storyLocalDataSource: FakeDataSource

    private lateinit var storyRepository: DefaultResultRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun createRepository(){
        storyRemoteDataSource = FakeDataSource(remoteStories.toMutableList())
        storyLocalDataSource = FakeDataSource(localStories.toMutableList())
        storyRepository = DefaultResultRepository(
            storyRemoteDataSource, storyLocalDataSource, Dispatchers.Main
        )
    }

    @Test
    fun `get stories from remote data source`() = mainCoroutineRule.runBlockingTest{
        val stories = storyRepository.getStories(true) as Result.Success
        assertThat(stories.data).isEqualTo(remoteStories)
    }




}