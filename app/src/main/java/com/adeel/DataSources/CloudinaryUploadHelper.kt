package com.adeel.DataSources

import android.content.Context
import android.util.Log
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback

class CloudinaryUploadHelper {
    companion object {
        private var isInitialized = false // Track initialization manually

        fun initializeCloudinary(context: Context) {
            if (!isInitialized) {
                val config = mapOf(
                    "cloud_name" to "curify", // Replace with your Cloudinary cloud name
                    "api_key" to "342546322121118", // Replace with your Cloudinary API key
                    "api_secret" to "3jRy5D2rTScLPFevFVsF8CNjvIM" // Replace with your Cloudinary API secret
                )
                MediaManager.init(context, config)
                isInitialized = true // Set the flag to true after initialization
                Log.d("Cloudinary", "MediaManager initialized successfully.")
            } else {
                Log.d("Cloudinary", "MediaManager is already initialized.")
            }
        }
    }

    fun uploadFile(
        filePath: String,
        onComplete: (Boolean, String?) -> Unit
    ) {
        MediaManager.get().upload(filePath)
            .callback(object : UploadCallback {
                override fun onStart(requestId: String) {
                    Log.d("Cloudinary", "Upload started: $requestId")
                }

                override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
                    val progress = (bytes.toDouble() / totalBytes * 100).toInt()
                    Log.d("Cloudinary", "Upload progress: $progress%")
                }

                override fun onSuccess(requestId: String, resultData: Map<*, *>) {
                    val fileUrl = (resultData["url"] as? String)?.replace("http://", "https://")
                    if (fileUrl != null) {
                        Log.d("Cloudinary", "Upload successful. URL: $fileUrl")
                        onComplete(true, fileUrl)
                    } else {
                        Log.e("Cloudinary", "URL not found in response")
                        onComplete(false, null)
                    }
                }

                override fun onError(requestId: String?, error: ErrorInfo?) {
                    Log.e("Cloudinary", "Upload failed: ${error?.description}")
                    onComplete(false, error?.description)
                }

                override fun onReschedule(requestId: String?, error: ErrorInfo?) {
                    Log.e("Cloudinary", "Upload rescheduled")
                }
            }).dispatch()
    }
}
