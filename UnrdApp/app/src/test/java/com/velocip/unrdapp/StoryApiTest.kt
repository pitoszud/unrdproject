package com.velocip.unrdapp

import com.velocip.unrdapp.data.models.StoryResult
import com.velocip.unrdapp.service.StoryApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class StoryApiTest {

    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(8080)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `shoud GET with query`(){
        val mockWebServer = MockWebServer()

    }


}