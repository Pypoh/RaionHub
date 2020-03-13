package com.example.raionhub.ui.auth.domain

import com.example.raionhub.utils.viewobject.Resource
import com.google.firebase.auth.FirebaseAuth

interface IAuth {

    suspend fun getAuthInstance(): Resource<FirebaseAuth>
}
