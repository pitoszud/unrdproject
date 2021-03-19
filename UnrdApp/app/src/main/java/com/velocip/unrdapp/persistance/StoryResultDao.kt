package com.velocip.unrdapp.persistance

import androidx.room.Dao
import androidx.room.Query
import com.velocip.unrdapp.data.StoryResult

@Dao
interface StoryResultDao {

    @Query("SELECT * FROM storyresults")
    suspend fun getAllBookings(): List<StoryResult>?
}