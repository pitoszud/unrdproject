package com.velocip.unrdapp.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stories")
data class Story(
    @field:PrimaryKey
    @field:NonNull
    val storyId: String,
    val created: String,
    val duration: String,
    val full_summary: String,
)
