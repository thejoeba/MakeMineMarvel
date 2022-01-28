package com.heckbot.makeminemarvel

import com.heckbot.ComicCoordinator
import com.heckbot.ComicsCallback
import com.heckbot.model.Comic

class TestComicsCoordinator(val testComics: List<Comic>): ComicCoordinator {
    var timesGetComics = 0

    override fun getComics(callback: ComicsCallback) {
        timesGetComics++
        callback(testComics)
    }
}