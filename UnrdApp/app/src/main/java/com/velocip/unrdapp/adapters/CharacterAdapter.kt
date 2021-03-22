package com.velocip.unrdapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.velocip.unrdapp.data.models.StoryCharacter
import com.velocip.unrdapp.databinding.StoryCharacterItemBinding
import com.velocip.unrdapp.viewmodels.StoryDetailsViewModel

class CharacterAdapter(private val viewModel: StoryDetailsViewModel): ListAdapter<StoryCharacter, RecyclerView.ViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item: StoryCharacter = getItem(position)
        (holder as CharacterViewHolder).bind(viewModel, item)
    }



    class CharacterViewHolder(private val binding: StoryCharacterItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(vm: StoryDetailsViewModel, item: StoryCharacter){
            with(binding){
                userCharacter = item
                viewModel = vm
                executePendingBindings()
            }
        }

        companion object{
            fun from(parent: ViewGroup): CharacterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StoryCharacterItemBinding.inflate(layoutInflater, parent, false)

                return CharacterViewHolder(
                        binding
                )
            }
        }
    }
}


class CharacterDiffCallback: DiffUtil.ItemCallback<StoryCharacter>(){
    override fun areItemsTheSame(oldItem: StoryCharacter, newItem: StoryCharacter): Boolean {
        return oldItem.characterId == newItem.characterId
    }

    override fun areContentsTheSame(oldItem: StoryCharacter, newItem: StoryCharacter): Boolean {
        return oldItem == newItem
    }

}