package com.heckbot.coreandroid.coordinator

import com.heckbot.ComicCoordinator
import com.heckbot.ComicsCallback
import com.heckbot.coreandroid.CoreAndroidDependencies

class NetworkComicCoordinator: ComicCoordinator {
    override fun getComics(callback: ComicsCallback) {
        val network = CoreAndroidDependencies.network
        network.setUrl("https://gateway.marvel.com:443/v1/public/comics")
        network.addParameters(
            "characters" to "1009610",
            "apikey" to API_KEY,
            "hasDigitalIssue" to "true",
            "series" to "1987",
            "orderBy" to "issueNumber",
        )
        network.addHeaders(
            "Referer" to "https://developer.marvel.com/"
        )
        network.request {
            val comics = CoreAndroidDependencies.jsonDeserializer.deserializeComicList(it)
            callback(comics)
        }
    }

    companion object {
        // public key from https://developer.marvel.com/account
        const val API_KEY = "7c15c8ecabe5bc9b275514acfe63f684"
    }
}