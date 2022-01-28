package com.heckbot.makeminemarvel

import com.heckbot.coreandroid.CoreAndroidDependencies
import com.heckbot.coreandroid.CoreAndroidDependencyContext
import com.heckbot.makeminemarvel.coordinator.OfflineRealDataComicCoordinator
import com.heckbot.makeminemarvel.repository.ComicRepositoryDefault
import com.heckbot.picassoimageloader.PicassoImageLoader

class DependencyContext {
    fun initialize() {
        CoreAndroidDependencyContext().initializeLocal()
        Dependencies.logger = CoreAndroidDependencies.logger
        Dependencies.comicsCoordinator = OfflineRealDataComicCoordinator()
        Dependencies.comicsRepository = ComicRepositoryDefault()
        Dependencies.imageLoader = PicassoImageLoader()
    }
}