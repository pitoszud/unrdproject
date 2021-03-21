package com.velocip.unrdapp.utils

class AppConstants {

    companion object{
        // room
        const val STORY_DATABASE_NAME = "story_database"

        // story api
        const val STORY_BASE_URL = "https://s3-eu-west-1.amazonaws.com/unrd-scratch/resp.json/"


        // http client
        val CONNECTION_TIMEOUT = 10L // 10 seconds
        val READ_TIMEOUT = 2L // 2 seconds
        val WRITE_TIMEOUT = 2L // 2 seconds
    }
}