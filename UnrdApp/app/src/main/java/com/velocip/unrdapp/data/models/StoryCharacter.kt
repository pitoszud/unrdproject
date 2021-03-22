package com.velocip.unrdapp.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import com.velocip.unrdapp.persistance.RoomTypeConverter

@JsonClass(generateAdapter = true)
@Entity(tableName = "storycharacter")
@TypeConverters(RoomTypeConverter::class)
data class StoryCharacter(
        @field:PrimaryKey
        @field:NonNull
        val characterId: String = "",
        val name: String = "",
        val imageUrl: String = ""

)
