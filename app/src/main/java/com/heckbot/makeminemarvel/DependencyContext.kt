package com.heckbot.makeminemarvel

import com.heckbot.coreandroid.CoreAndroidDependencies
import com.heckbot.coreandroid.CoreAndroidDependencyContext
import com.heckbot.coreandroid.coordinator.NetworkComicCoordinator
import com.heckbot.makeminemarvel.repository.ComicRepositoryDefault
import com.heckbot.picassoimageloader.PicassoImageLoader

class DependencyContext {
    fun initialize() {
        CoreAndroidDependencyContext().initializeLocal()
        Dependencies.logger = CoreAndroidDependencies.logger
        Dependencies.comicsCoordinator = NetworkComicCoordinator()
        Dependencies.comicsRepository = ComicRepositoryDefault()
        Dependencies.imageLoader = PicassoImageLoader()
    }
}