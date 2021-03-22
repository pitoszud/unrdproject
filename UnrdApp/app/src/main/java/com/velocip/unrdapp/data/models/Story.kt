package com.velocip.unrdapp.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import com.velocip.unrdapp.persistance.RoomTypeConverter

@TypeConverters(RoomTypeConverter::class)
@JsonClass(generateAdapter = true)
@Entity(tableName = "stories")
data class Story(
    @field:PrimaryKey
    @field:NonNull
    val storyId: String = "",
    val created: String = "",
    val duration: String = "",
    val full_summary: String = "",
    val name: String = "",
    val shortSummary: String = "",
    val price: Double? = 0.0,
    val ageFrom: Int = 0,
    val updated: String = "",
    val storyImage: String = "",
    val videoUrls: List<String> = emptyList(),
    val backgroundImage: String = "",
){
    var storyCharacters = listOf<StoryCharacter>()
}

