package com.velocip.unrdapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.velocip.unrdapp.adapters.CharacterAdapter
import com.velocip.unrdapp.databinding.FragmentStoryDetailsBinding
import com.velocip.unrdapp.viewmodels.StoryDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoryDetailsFragment: Fragment() {

    private val storyViewModel: StoryDetailsViewModel by activityViewModels()
    lateinit var binding: FragmentStoryDetailsBinding
    lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStoryDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = storyViewModel
            lifecycleOwner = viewLifecycleOwner
        }


        binding.loadButton.setOnClickListener {
            binding.loadButton.visibility = View.INVISIBLE
            storyViewModel.loadStories(true)
        }

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        subscribeToCharacters()
    }


    private fun subscribeToCharacters(){
        storyViewModel.storyCharacters.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()){
                characterAdapter.submitList(it)
            }
        })
    }


    private fun setupAdapter(){
        characterAdapter = CharacterAdapter(storyViewModel)
        val linearLayoutManager = LinearLayoutManager(activity)
        binding.characterList.apply {
            layoutManager = linearLayoutManager
            adapter = characterAdapter
        }
    }
}