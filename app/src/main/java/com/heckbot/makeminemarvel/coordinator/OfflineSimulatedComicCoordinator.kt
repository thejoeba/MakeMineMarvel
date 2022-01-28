package com.heckbot.makeminemarvel.coordinator

import com.heckbot.ComicCoordinator
import com.heckbot.ComicsCallback
import com.heckbot.model.Comic

class OfflineSimulatedComicCoordinator: ComicCoordinator {
    override fun getComics(callback: ComicsCallback) {
        callback(listOf(
            Comic(
                "How to Read Comics the Marvel Way (2021) #4",
                """"How to Read Comics the Marvel Way" concludes in a show-stopping finale!""",
                "http://i.annihil.us/u/prod/marvel/i/mg/9/00/61ae1d26c55f2"
            ),
            Comic(
                "The Amazing Spider-Man (2018) #79",
                "",
                "http://i.annihil.us/u/prod/marvel/i/mg/4/20/619e63a189b86"
            )
        ))
    }
}