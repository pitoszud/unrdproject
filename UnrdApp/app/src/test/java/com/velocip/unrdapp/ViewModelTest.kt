package com.velocip.unrdapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.viewmodels.StoryDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: StoryDetailsViewModel
    private lateinit var storyRepository: FakeTestRepository
    private lateinit var story: Story

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setupViewModel(){
        storyRepository = FakeTestRepository()
        story = Story(storyId = "345", name = "Story name")
        storyRepository.initStories(story)
        viewModel = StoryDetailsViewModel(storyRepository)
    }


    @Test
    fun `load stories should return error`(){
        storyRepository.setReturnError(true)
        viewModel.loadStories(true)

        assertThat(viewModel.empty.getOrAwaitValue()).isTrue()
    }

    @Test
    fun `load stories should return true`(){
        viewModel.loadStories(true)
        storyRepository.setReturnError(false)
        assertThat(viewModel.stories.getOrAwaitValue()).isEqualTo(listOf(story))
    }


    @Test
    @Ignore("live data is not set error")
    fun `load stories should return spinner true and spinner false`(){
        mainCoroutineRule.pauseDispatcher()
        viewModel.loadStories(true)
        assertThat(viewModel.dataLoading.getOrAwaitValue()).isTrue()
        mainCoroutineRule.resumeDispatcher()
        assertThat(viewModel.dataLoading.getOrAwaitValue()).isFalse()
    }


}