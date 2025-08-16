package com.adeel.repositories

import com.adeel.DataSources.CloudinaryUploadHelper


class StorageRepository {

    fun uploadFile(filePath:String,onComplete: (Boolean,String?) -> Unit){
        CloudinaryUploadHelper().uploadFile(filePath,onComplete)
    }

}