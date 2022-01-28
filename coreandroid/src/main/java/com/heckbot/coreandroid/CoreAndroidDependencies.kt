package com.heckbot.coreandroid

import com.heckbot.Logger
import com.heckbot.Network
import com.heckbot.JsonDeserializer

object CoreAndroidDependencies {
    lateinit var networkFactory: ()->Network
    val network: Network
        get() = networkFactory()
    lateinit var jsonDeserializer: JsonDeserializer
    lateinit var logger: Logger
}