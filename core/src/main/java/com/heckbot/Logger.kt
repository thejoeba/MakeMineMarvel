package com.heckbot

/**
 * @author Joey Heck
 *
 * Wrapper for Logging to console/Logcat/File
 *
 */
interface Logger {
    /**
     * @author Joey Heck
     *
     * handles debug level log requests
     *
     * @param tag [String] Label for message
     * @param message [String] Message to log
     */
    fun d(tag: String, message: String)
}