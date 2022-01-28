package com.heckbot.makeminemarvel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.heckbot.model.Comic
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ComicsViewModelTest {
    // Needed so setValue() can be called on LiveData, or else the test will crash due to not being on the main android thread
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    val testComicList = mutableListOf<Comic>()
    private val testLogger = TestLogger()
    private val testComicsCoordinator = TestComicsCoordinator(testComicList)
    private val testComicsRepository = TestComicsRepository(testComicList)
    private val testImageLoader = TestImageLoader()

    private lateinit var comicsViewModel: ComicsViewModel

    @Before
    fun setup() {
        Dependencies.logger = testLogger
        Dependencies.comicsCoordinator = testComicsCoordinator
        Dependencies.comicsRepository = testComicsRepository
        Dependencies.imageLoader = testImageLoader

        comicsViewModel = ComicsViewModel()
    }

    @Test
    fun `test Initial Values`() {
        assertEquals(testComicList, comicsViewModel.comicsLiveData.value)
        assertNull(comicsViewModel.selectedComicLiveData.value)
        assertEquals(ComicsViewModel.ViewState.List, comicsViewModel.viewStateLiveData.value)
    }

    @Test
    fun `ViewModel Lifecycle`() {
        val testComicsRepository = Dependencies.comicsRepository as TestComicsRepository
        assertEquals(1, testComicsRepository.timesSubscribeToComicsListCalled)
        assertEquals(0, testComicsRepository.timesUnsubscribeToComicList)

        comicsViewModel.onCleared()
        assertEquals(1, testComicsRepository.timesSubscribeToComicsListCalled)
        assertEquals(1, testComicsRepository.timesUnsubscribeToComicList)
    }

    @Test
    fun `test Selection Updates ViewState`() {
        val testComic1 = Comic("1","1")
        val testComic2 = Comic("2","2")
        testComicList.add(testComic1)
        testComicList.add(testComic2)

        comicsViewModel.comicSelected(0)
        assertEquals(testComic1, comicsViewModel.selectedComicLiveData.value)
        assertEquals(ComicsViewModel.ViewState.Details, comicsViewModel.calculateViewState())

        comicsViewModel.comicSelected(1)
        assertEquals(testComic2, comicsViewModel.selectedComicLiveData.value)
        assertEquals(ComicsViewModel.ViewState.Details, comicsViewModel.calculateViewState())

        comicsViewModel.comicCleared()
        assertNull(comicsViewModel.selectedComicLiveData.value)
        assertEquals(ComicsViewModel.ViewState.List, comicsViewModel.calculateViewState())
    }
}