package com.example.raionhub.repository.datasource.remote.auth.other

import com.example.raionhub.utils.viewobject.Resource
import com.google.firebase.auth.FirebaseAuth

interface IAuthRepo {

    suspend fun getAuthInstance(): Resource<FirebaseAuth>
}
