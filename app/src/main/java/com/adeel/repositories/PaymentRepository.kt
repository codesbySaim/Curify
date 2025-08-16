package com.adeel.repositories

import com.adeel.PaymentModule.PaymentTypeData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class PaymentRepository {
    val PaymentMethodCollection = FirebaseFirestore.getInstance().collection("PaymentMethod")


    suspend fun saveMethodCollection(paymentdata: PaymentTypeData): Result<Boolean> {
        try {
            val document = PaymentMethodCollection.document()
            paymentdata.id = document.id

            document.set(paymentdata).await()
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)

        }
    }

    fun getmethod()= PaymentMethodCollection
        .orderBy("timestamp", Query.Direction.DESCENDING)
        .limit(1)
        .snapshots()
        .map { snapshot -> snapshot.toObjects(PaymentTypeData::class.java) }
}


