package com.velocip.unrdapp.viewmodule


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.repository.StoryRepository
import com.velocip.unrdapp.utils.Result
import com.velocip.unrdapp.utils.Result.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryDetailsViewModel @Inject constructor(private val storyRepository: StoryRepository): ViewModel() {

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
        storyRepository.observeStories().switchMap { filterStories(it) }
    }
    val stories: LiveData<List<Story>> = _stories


    private val _storyId = MutableLiveData<String>()
    private val _story = _storyId.switchMap { storyId ->
        storyRepository.observeStory(storyId).map { processResult(it) }
    }
    val story: LiveData<Story?> = _story



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
                result.value = tasksResult.data
            }
        } else {
            result.value = emptyList()
            // show snackbar
        }

        return result
    }


    fun loadStories(forceUpdate: Boolean){
        _forceUpdate.value = forceUpdate
    }

}