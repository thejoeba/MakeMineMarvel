package com.heckbot.makeminemarvel.repository

import com.heckbot.ComicRepository
import com.heckbot.ComicSubscriberCallback
import com.heckbot.makeminemarvel.Dependencies
import com.heckbot.model.Comic

class ComicRepositoryDefault : ComicRepository {
    val callbacks = mutableMapOf<String, ComicSubscriberCallback>()
    var currentList = emptyList<Comic>()
    init {
        Dependencies.comicsCoordinator.getComics {
            currentList = it

            callbacks.forEach {
                it.value(currentList)
            }
        }
        callbacks.forEach {
            it.value(currentList)
        }
    }
    override fun subscribeToComicsList(tag: String, callback: ComicSubscriberCallback) {
        if (callbacks.containsKey(tag)) {
            throw Exception("Already subscribed")
        }
        callback(currentList)
        callbacks[tag] = callback
    }

    override fun unsubscribeToComicList(tag: String) {
        callbacks.remove(tag)
    }
}