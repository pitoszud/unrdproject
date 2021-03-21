package com.velocip.unrdapp.viewmodule


import androidx.lifecycle.*
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.repository.StoryRepository
import com.velocip.unrdapp.utils.Result
import com.velocip.unrdapp.utils.Result.*
import kotlinx.coroutines.launch

class StoryDetailsViewModel(private val storyRepository: StoryRepository): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _forceUpdate = MutableLiveData<Boolean>(false)

    private val _stories: LiveData<List<Story>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            _dataLoading.value = true
            viewModelScope.launch {
                storyRepository.refreshStories()
                _dataLoading.value = false
            }
        }
        storyRepository.observeStories().switchMap { filterTasks(it) }

    }

    private val _storyId = MutableLiveData<String>()
    private val _story = _storyId.switchMap { storyId ->
        storyRepository.observeStory(storyId).map { processResult(it) }
    }
    val task: LiveData<Story?> = _story



    private fun processResult(taskResult: Result<Story>): Story? {
        return if (taskResult is Success) {
            taskResult.data
        } else {
            // show Snackbar
            null
        }
    }


    private fun filterStories(tasksResult: Result<List<Story>>): LiveData<List<Story>> {
        val result = MutableLiveData<List<Story>>()

        if (tasksResult is Success) {
            viewModelScope.launch {
                result.value =
            }
        } else {
            result.value = emptyList()
            // show snackbar
        }

        return result
    }
}