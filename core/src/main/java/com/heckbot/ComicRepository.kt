package com.heckbot

import com.heckbot.model.Comic

typealias ComicSubscriberCallback = (List<Comic>) -> Unit

/**
 * @author Joey Heck
 *
 * Repository for Android MVVM Client
 *
 */
interface ComicRepository {
    /**
     * @author Joey Heck
     *
     * continuous asynchronous fetching of Comics
     *
     * @param tag [String] support multiple subscribers and unsubscribing by using a tag
     * @param callback [ComicSubscriberCallback] simple function that with [List<Comic>] parameter
     */
    fun subscribeToComicsList(tag: String, callback: ComicSubscriberCallback)


    /**
     * @author Joey Heck
     *
     * removes subscription
     *
     * @param tag [String] matching tag to previous subscribeToComicsList
     */
    fun unsubscribeToComicList(tag: String)
}