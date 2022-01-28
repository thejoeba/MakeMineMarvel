package com.heckbot.fakeimageloader

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.widget.ImageView
import com.heckbot.coreandroid.ImageLoader

class FakeImageLoader: ImageLoader {
    override fun loadBitmapFromUrlIntoImageView(url: String, view: ImageView) {
        val bitmap = Bitmap.createBitmap(200, 300, Bitmap.Config.ALPHA_8).apply {
            val canvas = Canvas(this)
            canvas.drawRect(0f, 0f, 200f, 300f, Paint().apply {
                isAntiAlias = true
                color = Color.BLACK
                style = Paint.Style.FILL
            })
        }

        view.setImageBitmap(bitmap)
    }
}