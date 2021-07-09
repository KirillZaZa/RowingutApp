package ru.kirilldev.rowingutapp.extensions

import android.content.Context

fun Context.dpToPx(dp: Int): Float{
    return dp * this.resources.displayMetrics.density
}

fun Context.pxToDp(px: Int): Float{
    return px / this.resources.displayMetrics.density
}