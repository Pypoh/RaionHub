package com.example.raionhub.ui.auth.login.domain

import com.example.raionhub.repository.datasource.remote.auth.login.ILoginRepo
import com.example.raionhub.utils.viewobject.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginImpl(private val loginRepository: ILoginRepo): ILogin {

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Resource<AuthResult?> = loginRepository.loginWithEmailAndPassword(email, password)

}