package com.adeel.repositories

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

import com.google.firebase.auth.userProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthRepository {

    suspend fun login(email: String, password: String): Result<FirebaseUser> {
        try {
            val result =
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
            return Result.success(result.user!!)

        } catch (e: Exception) {
            return Result.failure(e)

        }
    }

    suspend fun Signup(email: String, password: String, name: String): Result<FirebaseUser> {
        try {
            val result =
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
            val profileUpdates = userProfileChangeRequest {
                displayName = name

            }


            result.user?.updateProfile(profileUpdates)?.await()
            return Result.success(result.user!!)

        } catch (e: Exception) {
            return Result.failure(e)

        }
    }

    suspend fun resetpassword(email: String): Result<Boolean> {
        try {
            val result = FirebaseAuth.getInstance().sendPasswordResetEmail(email).await()
            return Result.success(true)


        } catch (e: Exception) {
            return Result.failure(e)

        }
    }

    fun getcurrentuser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    fun logout(): Result<Boolean> {

        try {

            val result = Firebase.auth.signOut()
            return Result.success(true)
        }


        catch (e: Exception) {
            return Result.failure(e)

        }

    }

}