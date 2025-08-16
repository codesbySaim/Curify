package com.adeel.MedicalStoreModule

import android.media.MediaCas
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adeel.curify.Medicine
import com.adeel.repositories.MedicineRepository
import com.adeel.repositories.StorageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MedicineViewModel : ViewModel(){
    val medicinerepository = MedicineRepository()
    val storageRepository = StorageRepository()
    val isSuccessfullyDeleted = MutableStateFlow<Boolean?>(null)
    val isSuccessfullySaved = MutableStateFlow<Boolean?>(null)
    val failureMessage = MutableStateFlow<String?>(null)

    fun uploadImageAndmedicine(imagePath: String, medicine: Medicine) {
        storageRepository.uploadFile(imagePath, onComplete ={ success, result ->
            if (success) {
                medicine.image=result!!
                savemedicine(medicine)
            }
            else failureMessage.value=result
        })
    }
    fun savemedicine(medicine: Medicine) {
        viewModelScope.launch {
            val result = medicinerepository.savemedicine(medicine)
            if (result.isSuccess) {
                isSuccessfullySaved.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }
    fun deletePetShop(medid: String) {
        viewModelScope.launch {
            val result = medicinerepository.deletemedicine(medid)
            if (result.isSuccess) {
                isSuccessfullyDeleted.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }


    fun updatemed(med: Medicine) {
        viewModelScope.launch {
            val result = medicinerepository.updatemedicine(med)
            if (result.isSuccess) {
                isSuccessfullySaved.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }
}