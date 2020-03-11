package com.example.raionhub.ui.auth.login.domain

import com.example.raionhub.utils.viewobject.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface ILogin {

    suspend fun loginWithEmailAndPassword(email: String, password: String): Resource<AuthResult?>

}