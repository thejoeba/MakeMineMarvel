package com.heckbot

import com.heckbot.model.Comic

/**
 * @author Joey Heck
 *
 * Wrapper for Json library
 *
 */
interface JsonDeserializer {
    /**
     * @author Joey Heck
     *
     * removes subscription
     *
     * @param value [String] json string to be deserialized. Expects full string from Marvel API
     * @return [List<Comic>]
     */
    fun deserializeComicList(value: String): List<Comic>
}