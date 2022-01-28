package com.heckbot.coreandroid

import com.heckbot.KotlinXDeserializer
import com.heckbot.KtorNetwork


class CoreAndroidDependencyContext {
    fun initializeLocal() {
        CoreAndroidDependencies.networkFactory = ::KtorNetwork
        CoreAndroidDependencies.jsonDeserializer = KotlinXDeserializer()
        CoreAndroidDependencies.logger = AndroidLogger()
    }
}