package com.heckbot.makeminemarvel.details

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heckbot.makeminemarvel.*
import com.heckbot.model.Comic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

    val testComic = Comic("TestTitle", "TestDescription", "TestUrl")
    val testComicList = mutableListOf<Comic>(testComic)
    private val testLogger = TestLogger()
    private val testComicsCoordinator = TestComicsCoordinator(testComicList)
    private val testComicsRepository = TestComicsRepository(testComicList)
    private val testImageLoader = TestImageLoader()

    @Before
    fun setup() {
        Dependencies.logger = testLogger
        Dependencies.comicsCoordinator = testComicsCoordinator
        Dependencies.comicsRepository = testComicsRepository
        Dependencies.imageLoader = testImageLoader
    }

    @Test
    fun testDetailsFragmentUI() {
        val scenario = launchFragmentInContainer<DetailsFragment>()
        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onFragment { fragment ->
            val tvTitle = fragment.view?.findViewById<TextView>(R.id.tvTitle)
            val tvDetails = fragment.view?.findViewById<TextView>(R.id.tvDetails)

            fragment.comicsViewModel.comicSelected(0)

            assertEquals(testComic.title, tvTitle?.text)
            assertEquals(testComic.details, tvDetails?.text)
            assertEquals(1, testImageLoader.timesLoadBitmapFromUrlIntoImageViewCalled)
            assertEquals(testComic.coverUrl, testImageLoader.lastUrlCalled)
        }
    }
}