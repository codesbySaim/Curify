package com.adeel.MedicalStoreModule

import android.media.MediaCas
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adeel.curify.Medicine
import com.adeel.curify.MyCartData
import com.adeel.repositories.MedicineRepository
import com.adeel.repositories.MyCartRepository
import com.adeel.repositories.StorageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MyCartViewModel : ViewModel(){
    val medicinerepository = MyCartRepository()
    val storageRepository = StorageRepository()
    val isSuccessfullyDeleted = MutableStateFlow<Boolean?>(null)
    val isSuccessfullySaved = MutableStateFlow<Boolean?>(null)
    val failureMessage = MutableStateFlow<String?>(null)


    fun savemycart(mycart: MyCartData) {
        viewModelScope.launch {
            val result = medicinerepository.savemycart(mycart)
            if (result.isSuccess) {
                isSuccessfullySaved.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }
    fun deletemycart(medid: String) {
        viewModelScope.launch {
            val result = medicinerepository.deletemycart(medid)
            if (result.isSuccess) {
                isSuccessfullyDeleted.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }
    fun getTotalPrice(): Double {
        return data.value?.sumOf { it.price.toDoubleOrNull() ?: 0.0 } ?: 0.0
    }

    val mycartrepo = MyCartRepository()

    val failuremessge = MutableStateFlow<String?>(null)
    val data= MutableStateFlow<List<MyCartData>?>(null)
    // val dataone= MutableStateFlow<Medicine?>(null)


    init {
        readmycart()
    }



    fun readmycart(){
        viewModelScope.launch {
            mycartrepo.getmycart().catch {
                failuremessge.value=it.message
            }

                .collect{
                    data.value=it
                }
        }
    }

}