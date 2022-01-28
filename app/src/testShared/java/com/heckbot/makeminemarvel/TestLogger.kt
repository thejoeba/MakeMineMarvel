package com.heckbot.makeminemarvel

import com.heckbot.Logger

class TestLogger: Logger {
    var timesDCalled = 0
    override fun d(tag: String, message: String) {
        timesDCalled++
        println("$tag: $message")
    }
}