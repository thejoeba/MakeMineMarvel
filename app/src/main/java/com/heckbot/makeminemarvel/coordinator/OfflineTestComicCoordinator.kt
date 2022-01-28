package com.heckbot.makeminemarvel.coordinator

import com.heckbot.ComicCoordinator
import com.heckbot.ComicsCallback
import com.heckbot.model.Comic

class OfflineTestComicCoordinator: ComicCoordinator {
    override fun getComics(callback: ComicsCallback) {
        callback(listOf(Comic("Test", "Test"), Comic("Test2", "Test2")))
    }
}