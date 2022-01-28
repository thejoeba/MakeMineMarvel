package com.heckbot

import com.heckbot.model.Comic
import com.heckbot.model.MarvelResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class KotlinXDeserializer: JsonDeserializer {
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
    override fun deserializeComicList(value: String): List<Comic> {
        val marvelResponse = json.decodeFromString<MarvelResponse>(value)
        return marvelResponse.data?.results?.map {
            it.toComic()
        } ?: emptyList()
    }
}