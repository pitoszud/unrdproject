package com.velocip.unrdapp.data.models

import androidx.room.Entity

@Entity(tableName = "stories")
data class Story(
    val storyId: String
)


fun Story.toStoryDto(): StoryDto {
    return StoryDto(
        story_id = storyId
    )
}
