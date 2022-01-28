package com.heckbot

import com.heckbot.model.Comic

typealias ComicsCallback = (List<Comic>) -> Unit

/**
 * @author Joey Heck
 *
 * Coordinator to be used by Repository to access data
 *
 */
interface ComicCoordinator {
    /**
     * @author Joey Heck
     *
     * asynchronous one time fetching of Comics
     *
     * @param callback [ComicSubscriberCallback] simple function that with [List<Comic>] parameter
     */
    fun getComics(callback: ComicsCallback)
}