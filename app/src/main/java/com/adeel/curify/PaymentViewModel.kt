package com.adeel.curify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adeel.PaymentModule.PaymentTypeData
import com.adeel.repositories.PaymentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PaymentViewModel: ViewModel() {
    val paymentRepository = PaymentRepository()
  //  val storageRepository = StorageRepository()
    val isSuccessfullyDeleted = MutableStateFlow<Boolean?>(null)
    val isSuccessfullySaved = MutableStateFlow<Boolean?>(null)
    val data = MutableStateFlow<PaymentTypeData?>(null)


    val failureMessage = MutableStateFlow<String?>(null)
    fun savepaymenttype(paymentdata: PaymentTypeData) {
        viewModelScope.launch {
            val result = paymentRepository.saveMethodCollection(paymentdata)
            if (result.isSuccess) {
                isSuccessfullySaved.value = true
            } else {
                failureMessage.value = result.exceptionOrNull()?.message
            }
        }
    }
    fun readpetshop() {
        viewModelScope.launch {
            paymentRepository.getmethod()
                .catch { e ->
                    failureMessage.value = e.message ?: "Unknown error occurred"
                }
                .collect {
                    // Update LiveData with the first item or null if empty
                    data.value = it.firstOrNull()
                }
        }
    }

    // In your ViewModel, declare this LiveData




}