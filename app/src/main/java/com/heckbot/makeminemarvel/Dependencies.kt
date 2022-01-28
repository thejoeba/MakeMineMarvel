package com.heckbot.makeminemarvel

import com.heckbot.ComicCoordinator
import com.heckbot.ComicRepository
import com.heckbot.Logger
import com.heckbot.coreandroid.ImageLoader

object Dependencies {
    lateinit var comicsRepository: ComicRepository
    lateinit var comicsCoordinator: ComicCoordinator
    lateinit var imageLoader: ImageLoader
    lateinit var logger: Logger
}