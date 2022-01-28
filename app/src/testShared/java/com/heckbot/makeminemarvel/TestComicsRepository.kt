package com.heckbot.makeminemarvel

import com.heckbot.ComicRepository
import com.heckbot.ComicSubscriberCallback
import com.heckbot.model.Comic

class TestComicsRepository(val testComics: List<Comic>): ComicRepository {
    var timesSubscribeToComicsListCalled = 0
    var timesUnsubscribeToComicList = 0

    override fun subscribeToComicsList(tag: String, callback: ComicSubscriberCallback) {
        timesSubscribeToComicsListCalled++
        callback(testComics)
    }

    override fun unsubscribeToComicList(tag: String) {
        timesUnsubscribeToComicList++
    }
}