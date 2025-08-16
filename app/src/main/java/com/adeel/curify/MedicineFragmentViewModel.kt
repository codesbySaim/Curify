package com.adeel.curify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adeel.repositories.MedicineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MedicineFragmentViewModel: ViewModel(){
    val medicinerepo = MedicineRepository()

    val failuremessge = MutableStateFlow<String?>(null)
    val data= MutableStateFlow<List<Medicine>?>(null)
   // val dataone= MutableStateFlow<Medicine?>(null)


    init {
        readmedicine()
    }




    fun readmedicine(){
        viewModelScope.launch {
            medicinerepo.getmedicine().catch {
                failuremessge.value=it.message
            }

                .collect{
                    data.value=it
                }
        }
    }

}