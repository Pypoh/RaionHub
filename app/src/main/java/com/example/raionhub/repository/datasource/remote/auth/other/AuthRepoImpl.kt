package com.example.raionhub.repository.datasource.remote.auth.other

import com.example.raionhub.utils.viewobject.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class AuthRepoImpl : IAuthRepo {
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override suspend fun getAuthInstance(): Resource<FirebaseAuth> {
        return try {
            Resource.Success(mAuth)
        } catch (e: FirebaseAuthException) {
            Resource.Failure(e)
        }
    }
}
