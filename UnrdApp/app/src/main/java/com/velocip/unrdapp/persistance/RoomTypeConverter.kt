package com.velocip.unrdapp.persistance

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.velocip.unrdapp.data.models.StoryCharacter
import java.util.*

class RoomTypeConverter {

    private val moshi = Moshi.Builder().build()

    private val storyCharacterType = Types.newParameterizedType(
            List::class.java,
            StoryCharacter::class.java
    )
    private val storyCharacterAdapter: JsonAdapter<List<StoryCharacter>> = moshi.adapter(storyCharacterType)

    val listStringType = Types.newParameterizedType(
            List::class.java,
            String::class.java
    )
    val listStringAdapter: JsonAdapter<List<String>> = moshi.adapter(listStringType)


    @TypeConverter
    fun characterListToJson(bookings: List<StoryCharacter>): String{
        return storyCharacterAdapter.toJson(bookings)
    }

    @TypeConverter
    fun jsonToCharacterList(jsonCharacterStr: String?): List<StoryCharacter>{
        if (jsonCharacterStr == null){
            return Collections.emptyList()
        }
        return storyCharacterAdapter.fromJson(jsonCharacterStr) ?: emptyList()
    }




    @TypeConverter
    fun jsonStringToList(data: String?): List<String>{
        if(data == null){
            return Collections.emptyList<String>()
        }

        return listStringAdapter.fromJson(data) ?: emptyList()
    }

    @TypeConverter
    fun listStringToJson(venueList: List<String>): String{
        return listStringAdapter.toJson(venueList)
    }
}