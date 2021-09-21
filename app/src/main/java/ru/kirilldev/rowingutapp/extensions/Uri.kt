package ru.kirilldev.rowingutapp.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.FileNotFoundException
import java.io.IOException
import kotlin.jvm.Throws

@Throws(FileNotFoundException::class, IOException::class)
fun Uri.uriToBitmap(context: Context): Bitmap? {
    //
    val inputStream = context.contentResolver.openInputStream(this)!!
    val boundsOptions = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
        inPreferredConfig = Bitmap.Config.ARGB_8888
    }
    //
    BitmapFactory.decodeStream(inputStream, null, boundsOptions)
    inputStream.close()

    //
    boundsOptions.apply {
        if (this.outWidth == -1 || this.outHeight == -1)
            return@uriToBitmap null
    }

    return decodeToNewSize(boundsOptions, context, this)
}

/** Help - functions **/

fun getSampleSize(options: BitmapFactory.Options): Int {
    val reqHeight = 200
    val reqWidth = 200

    val sampleSize = if (options.outHeight > reqHeight || options.outWidth > reqWidth) {
        val heightRatio = Math.round((options.outHeight / reqHeight).toFloat())
        val widthRatio = Math.round((options.outWidth / reqWidth).toFloat())

        if (heightRatio < widthRatio) heightRatio else widthRatio
    } else 1

    return sampleSize
}

fun decodeToNewSize(
    boundsOptions: BitmapFactory.Options,
    context: Context,
    uri: Uri
): Bitmap?{

    val bitmapOptions = BitmapFactory.Options().apply {
        inSampleSize = getSampleSize(boundsOptions)
        inPreferredConfig = Bitmap.Config.ARGB_8888
    }

    val inputStream = context.contentResolver.openInputStream(uri)!!
    val bitmap = BitmapFactory.decodeStream(inputStream, null, bitmapOptions)

    inputStream.close()

    return bitmap
}




