package com.velocip.unrdapp.viewmodels


import android.util.Log
import androidx.lifecycle.*
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.data.models.StoryCharacter
import com.velocip.unrdapp.repository.StoryRepository
import com.velocip.unrdapp.utils.Result
import com.velocip.unrdapp.utils.Result.*
import com.velocip.unrdapp.utils.Result.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryDetailsViewModel @Inject constructor(private val storyRepository: StoryRepository): ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _forceUpdate = MutableLiveData(false)

    private val _stories: LiveData<List<Story>> = _forceUpdate.switchMap { forceUpdate ->
        Log.d(TAG, "$forceUpdate")
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


    private val _story: LiveData<Story> = _stories.map {
        if(it.isNotEmpty()){
            return@map it[0]
        }else
            return@map Story()
    }
    val story: LiveData<Story> = _story


    private val _storyCharacters: LiveData<List<StoryCharacter>> = stories.switchMap { storyList ->
        return@switchMap processStoryCharacters(storyList)
    }
    val storyCharacters: LiveData<List<StoryCharacter>> = _storyCharacters

    val empty: LiveData<Boolean> = stories.map {
        it.isNullOrEmpty()
    }

    val backgroundImage: LiveData<String> = _stories.map {
        if(it.isNotEmpty()){
            return@map it[0].backgroundImage
        }else
            return@map ""
        }





    private fun processStoryCharacters(storyList: List<Story>): MutableLiveData<List<StoryCharacter>> {
        val storyCharactersLiveData = MutableLiveData<List<StoryCharacter>>()
        if (storyList.isNotEmpty()) {
            storyCharactersLiveData.value = storyList[0].storyCharacters
        } else {
            storyCharactersLiveData.value = emptyList()
        }
        return storyCharactersLiveData
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

    companion object {
        const val TAG = "StoryDetailsViewModel"
    }

}