package com.example.raionhub.repository.datasource.remote.auth.login

import com.example.raionhub.utils.viewobject.Resource
import com.google.firebase.auth.AuthResult

interface ILoginRepo {

    suspend fun loginWithEmailAndPassword(email: String, password: String): Resource<AuthResult?>


}