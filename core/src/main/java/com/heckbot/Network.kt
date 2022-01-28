package com.heckbot

typealias NetworkCallback = (String)->Unit

/**
 * @author Joey Heck
 *
 * Wrapper for Network library
 *
 */
interface Network {
    /**
     * @author Joey Heck
     *
     * make asynchronous call to endpoint
     *
     * @param callback [NetworkCallback] simple function that with [String] parameter
     */
    fun request(callback: NetworkCallback)
    /**
     * @author Joey Heck
     *
     * sets the destination URL of the network request
     *
     * @param url [String]
     */
    fun setUrl(url: String)
    /**
     * @author Joey Heck
     *
     * sets the request parameters of the network request
     *
     * @param parameters [vararg Pair<String, String>] key value pairs of parameters
     */
    fun addParameters(vararg parameters: Pair<String, String>)
    /**
     * @author Joey Heck
     *
     * sets the request headers of the network request
     *
     * @param headers [vararg Pair<String, String>] key value pairs of a header
     */
    fun addHeaders(vararg headers: Pair<String, String>)
}