package com.heckbot.makeminemarvel

import android.widget.ImageView
import com.heckbot.coreandroid.ImageLoader

class TestImageLoader: ImageLoader {
    var timesLoadBitmapFromUrlIntoImageViewCalled = 0
    var lastUrlCalled: String? = null

    override fun loadBitmapFromUrlIntoImageView(url: String, view: ImageView) {
        timesLoadBitmapFromUrlIntoImageViewCalled++
        lastUrlCalled = url
    }
}