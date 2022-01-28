package com.heckbot.picassoimageloader

import android.widget.ImageView
import com.heckbot.coreandroid.ImageLoader
import com.squareup.picasso.Picasso

class PicassoImageLoader: ImageLoader {
    init {
        Picasso.get().isLoggingEnabled = true
    }

    override fun loadBitmapFromUrlIntoImageView(url: String, view: ImageView) {
        Picasso.get()
            .load(url)
            .into(view)
    }
}