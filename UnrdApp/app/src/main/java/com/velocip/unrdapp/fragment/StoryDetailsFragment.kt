package com.velocip.unrdapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.velocip.unrdapp.databinding.FragmentStoryDetailsBinding
import com.velocip.unrdapp.viewmodule.StoryDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoryDetailsFragment: Fragment() {

    private val storyViewModel: StoryDetailsViewModel by activityViewModels()
    lateinit var binding: FragmentStoryDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStoryDetailsBinding.inflate(inflater, container, false).apply {
            viewModel = storyViewModel
        }


        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        storyViewModel.loadStories(true)
    }
}