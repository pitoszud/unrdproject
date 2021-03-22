package com.velocip.unrdapp.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.velocip.unrdapp.data.models.Story
import com.velocip.unrdapp.utils.AppConstants


@Database(entities = [
    Story::class
],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract  fun storyResultDao(): StoryDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase {
            return instance
                ?: synchronized(this){
                    instance
                        ?: buildDatabase(
                            context
                        ).also {
                            instance = it
                        }
                }
        }


        /*
        * Simple app so no addCallback or addMigrations necessary
        * */
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,AppConstants.STORY_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}