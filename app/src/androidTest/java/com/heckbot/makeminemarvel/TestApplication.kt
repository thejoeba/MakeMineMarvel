package com.heckbot.makeminemarvel

import android.app.Application
import com.heckbot.model.Comic

class TestApplication: Application() {
    companion object {
        val testComic1 = Comic("TestTitle", "TestDescription", "TestUrl")
        val testComic2 = Comic("TestTitle", "TestDescription", "TestUrl")
        val testComicList = mutableListOf<Comic>(testComic1, testComic2)
        private val testLogger = TestLogger()
        private val testComicsCoordinator = TestComicsCoordinator(testComicList)
        private val testComicsRepository = TestComicsRepository(testComicList)
        private val testImageLoader = TestImageLoader()
    }

    override fun onCreate() {
        super.onCreate()

        DependencyContext().initialize()
        Dependencies.logger = testLogger
        Dependencies.comicsCoordinator = testComicsCoordinator
        Dependencies.comicsRepository = testComicsRepository
        Dependencies.imageLoader = testImageLoader
    }
}