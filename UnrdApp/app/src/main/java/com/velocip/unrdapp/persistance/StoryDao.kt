package com.velocip.unrdapp.persistance

import androidx.lifecycle.LiveData
import androidx.room.*
import com.velocip.unrdapp.data.Story


@Dao
interface StoryDao {

    /**
     * Observes list of stories.
     *
     * @return all stories.
     */
    @Query("SELECT * FROM stories")
    fun observeStories(): LiveData<List<Story>>

    /**
     * Observes a single story.
     *
     * @param storyId the story id.
     * @return the story with storyId.
     */
    @Query("SELECT * FROM stories WHERE storyId = :storyId")
    fun observeStoryById(storyId: String): LiveData<Story>

    /**
     * Select all stories from the stories table.
     *
     * @return all stories.
     */
    @Query("SELECT * FROM stories")
    suspend fun getStories(): List<Story>

    /**
     * Select a story by id.
     *
     * @param storyId the story id.
     * @return the story with storyId.
     */
    @Query("SELECT * FROM stories WHERE storyId = :storyId")
    suspend fun getStoryById(storyId: String): Story?

    /**
     * Insert a story in the database. If the story already exists, replace it.
     *
     * @param story the story to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(story: Story)

    /**
     * Update a story.
     *
     * @param story story to be updated
     * @return the number of stories updated. This should always be 1.
     */
    @Update
    suspend fun updateStory(story: Story): Int


    /**
     * Delete a story by id.
     *
     * @return the number of stories deleted. This should always be 1.
     */
    @Query("DELETE FROM stories WHERE storyId = :storyId")
    suspend fun deleteStoryById(storyId: String): Int

    /**
     * Delete all stories.
     */
    @Query("DELETE FROM stories")
    suspend fun deleteStories()
}