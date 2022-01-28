package com.heckbot.model

import kotlinx.serialization.Serializable

@Serializable
class MarvelResponseComic {
//    val id: Int = 0
//    val digitalId: Int = 0
    val title: String = ""
//    val issueNumber: Int = 0
//    val variantDescription: String = ""
    val description: String? = ""
//    val modified: String = ""
//    val isbn: String = ""
//    val upc: String = ""
//    val diamondCode: String = ""
//    val ean: String = ""
//    val issn: String = ""
//    val format: String = ""
//    val pageCount: Int = 0
//    val resourceURI: String = ""
    val thumbnail: MarvelResponseThumbnail? = null

    fun toComic(): Comic {
        return Comic(title, description ?: "", thumbnail?.let {it.path + "/portrait_uncanny.jpg" })
    }
}