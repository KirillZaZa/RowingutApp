package ru.kirilldev.rowingutapp.data.firebase.storage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.*
import ru.kirilldev.rowingutapp.application.RowingutApplication
import java.io.InputStream
import kotlin.random.Random

class ImageStorage {

    private val storage = Firebase.storage
    private val context = RowingutApplication.instance.applicationContext
    private val scope = RowingutApplication.scope

    companion object {
        private const val STORAGE_TAG = "storage_tag"
        private const val PATH = "images/"
    }



    private fun generateFileName() =
        (Random.nextInt(10000, 20000) * 31).toString()



//    fun saveImageToInternalStorage(uri: Uri){
//        scope.launch(Dispatchers.IO) {
//            try {
//                val cWrapper = ContextWrapper(context.applicationContext)
//                val directory = cWrapper.getDir("images", Context.MODE_PRIVATE)
//                val path = File(directory,"")
//                val bitmap = uri.uriToBitmap(context)
//
//
//
//            }catch (e: Throwable){
//                Log.e(STORAGE_TAG, "saveImageToInternalStorage: ${e.message}")
//            }
//        }
//    }
//
//    fun getImageFromInternalStorage(path: String): Bitmap{
//
//    }

    fun uploadImage(
        imageUri: Uri,
        callback: (String) -> Unit
    ) {

        scope.launch {
            var processStatus: Status? = null

            try {

                withContext(Dispatchers.IO) {
                    val reference = storage.getReference(PATH + generateFileName())

                    reference.putFile(imageUri)
                        .addOnSuccessListener {

                            Log.d(STORAGE_TAG, "uploadImage: ${Status.SUCCESS}")
                            processStatus = Status.SUCCESS

                        }
                        .addOnFailureListener {

                            Log.e(STORAGE_TAG, "uploadImage: ${it.message}")
                            processStatus = Status.FAILED

                        }
                }

                callback(processStatus!!.name)

            } catch (e: CancellationException) {
                Log.e(STORAGE_TAG, "uploadImage: ")
                callback(processStatus!!.name)
            }
        }


    }

    fun downloadImage(fileName: String?, callback: (Bitmap?) -> Unit) {
        fileName ?: return
        scope.launch {

            try {
                withContext(Dispatchers.IO) {
                    val reference = storage.getReference(PATH + fileName)
                    Glide.with(context)
                        .asBitmap()
                        .load(reference)
                        .centerCrop()
                        .circleCrop()
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                callback(resource)
                            }

                            override fun onLoadCleared(placeholder: Drawable?) {}
                        })
                }
            } catch (e: CancellationException) {
                Log.e(STORAGE_TAG, "downloadImage:${e.message}")
                callback(null)
            }
        }

    }





}

@GlideModule
class CustomGlideModule: AppGlideModule(){

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.append(
            StorageReference::class.java,
            InputStream::class.java,
            FirebaseImageLoader.Factory()
        )
    }

}


enum class Status {
    SUCCESS, FAILED
}