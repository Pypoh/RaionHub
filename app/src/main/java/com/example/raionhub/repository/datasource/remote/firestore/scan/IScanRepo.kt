package com.example.raionhub.repository.datasource.remote.firestore.scan

import com.example.raionhub.repository.model.UserInRoom
import com.example.raionhub.utils.viewobject.Resource
import java.util.Date

interface IScanRepo {

    suspend fun registerUserIntoRoom(nim: String): Resource<UserInRoom>
}