package com.example.raionhub.ui.auth.domain

import com.example.raionhub.repository.datasource.remote.auth.other.IAuthRepo
import com.example.raionhub.utils.viewobject.Resource
import com.google.firebase.auth.FirebaseAuth

class AuthImpl(private val authRepository: IAuthRepo) : IAuth {

    override suspend fun getAuthInstance(): Resource<FirebaseAuth> = authRepository.getAuthInstance()
}
