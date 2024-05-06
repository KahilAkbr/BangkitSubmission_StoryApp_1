package com.example.storygram.utils

import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.storygram.R

class StatusBarColor {
    companion object {
        fun change(context: Context) {
            if (context is Activity) {
                val color = ContextCompat.getColor(context, R.color.primary_blue_color)
                context.window.statusBarColor = color
            }
        }
    }
}