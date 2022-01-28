package com.heckbot.coreandroid

import android.widget.ImageView

/**
 * @author Joey Heck
 *
 * ImageLoader to place bitmaps in ImageViews from a URL
 *
 */
interface ImageLoader {
    /**
     * @author Joey Heck
     *
     * retrieves and sets a bitmap from URL to the passed in [ImageView]
     *
     * @param url [String] full URL to be fetched
     * @param view [ImageView] view to load image into once retrieved
     */
    fun loadBitmapFromUrlIntoImageView(url: String, view: ImageView)
}