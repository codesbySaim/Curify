package com.adeel.repositories

import com.adeel.curify.Medicine
import com.adeel.curify.MyCartData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class MyCartRepository {
    val MyCartCollection = FirebaseFirestore.getInstance().collection("MyCartData")


    suspend fun savemycart(mycart: MyCartData): Result<Boolean> {
        try {
            val document = MyCartCollection.document()
            mycart.id = document.id
            document.set(mycart).await()
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)

        }
    }
    suspend fun deletemycart(cartId: String): Result<Boolean> {
        return try {
            MyCartCollection.document(cartId).delete().await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    fun getmycart() = MyCartCollection
        .snapshots()
        .map {it.toObjects(MyCartData::class.java) }
}
