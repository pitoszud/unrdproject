package com.velocip.unrdapp.data

import androidx.room.Entity


@Entity(tableName = "storyresults")
data class StoryResult(
    val story_id: Int
)
