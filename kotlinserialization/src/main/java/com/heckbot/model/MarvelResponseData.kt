package com.heckbot.model

import kotlinx.serialization.Serializable

@Serializable
class MarvelResponseData {
//    val offset: Int = 0
//    val limit: Int = 0
//    val total: Int = 0
//    val count: Int = 0
    val results: List<MarvelResponseComic>? = null
}