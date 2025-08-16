package com.adeel.repositories

import com.adeel.curify.Medicine
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class MedicineRepository {
    val MedicineCollection = FirebaseFirestore.getInstance().collection("MedicineData")


    suspend fun savemedicine(medicine: Medicine): Result<Boolean> {
        try {
            val document = MedicineCollection.document()
            medicine.id = document.id
            document.set(medicine).await()
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)

        }
    }
    suspend fun deletemedicine(medId: String): Result<Boolean> {
        return try {
            MedicineCollection.document(medId).delete().await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun updatemedicine(med: Medicine): Result<Boolean> {
        try {
            val document = MedicineCollection.document(med.id)
            document.set(med).await()
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    fun getmedicine() = MedicineCollection
        .snapshots()
        .map {it.toObjects(Medicine::class.java) }
}
