package com.example.raionhub.repository.datasource.remote.firestore.scan

import android.util.Log
import com.example.raionhub.repository.model.UserInRoom
import com.example.raionhub.utils.Constants
import com.example.raionhub.utils.viewobject.Resource
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firestore.v1.DocumentTransform
import kotlinx.coroutines.tasks.await
import java.util.*
import kotlin.collections.HashMap

class ScanRepoImpl : IScanRepo {
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private var resultObj: UserInRoom? = null

    override suspend fun registerUserIntoRoom(nim: String): Resource<UserInRoom> {
        try {
            val data = hashMapOf(
                Constants.NIM to nim,
                Constants.TIME_ENTER to FieldValue.serverTimestamp()
            )

            val roomRef = db.collection(Constants.ROOM_COLLECTION)

            val resultRef = roomRef.document()

            resultRef.set(data).await()

            resultRef.get().addOnSuccessListener {
                resultObj = it.toObject(UserInRoom::class.java)
            }.addOnFailureListener {
                throw it
            }

            return Resource.Success(resultObj!!)
        } catch (e: FirebaseFirestoreException) {
            return Resource.Failure(e.cause!!)
        }
    }
}