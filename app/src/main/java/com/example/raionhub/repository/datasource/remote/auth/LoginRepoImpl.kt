package com.example.raionhub.repository.datasource.remote.auth

import com.example.raionhub.repository.datasource.remote.auth.login.ILoginRepo
import com.example.raionhub.utils.viewobject.Resource
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.yield

class LoginRepoImpl : ILoginRepo {
    val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override suspend fun loginWithEmailAndPassword(email: String, password: String): Resource<AuthResult?> {
        return try {
            val data = mAuth
                .signInWithEmailAndPassword(email, password)
                .await()

            Resource.Success(data)
        } catch (e: FirebaseAuthException) {
            Resource.Failure(e)
        }


    }

}