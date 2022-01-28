package com.heckbot.coreandroid

import android.util.Log
import com.heckbot.Logger

class AndroidLogger: Logger {
    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}