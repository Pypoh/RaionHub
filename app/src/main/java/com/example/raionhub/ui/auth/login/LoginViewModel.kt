package com.example.raionhub.ui.auth.login

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.raionhub.ui.auth.login.domain.ILogin
import com.example.raionhub.utils.Constants
import com.example.raionhub.utils.CustomException
import com.example.raionhub.utils.viewobject.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val useCase: ILogin) : ViewModel() {

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    lateinit var result: LiveData<Resource<AuthResult?>>

    // TODO: Bersihin, tambahin timeout login

        fun loginWithEmailAndPassword() {
            Log.d("LoginDEBUG: ", "loginDebugOutside: ${email.value}")
        result = liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                Log.d("LoginDEBUG: ", "loginDebug: ${email.value}")
                if (email.value.isNullOrEmpty() && password.value.isNullOrEmpty()) {
                    emit(Resource.Failure(CustomException("Email or Password can't be blank")))
                } else {
                    val loginAuthResult = useCase.loginWithEmailAndPassword(email = email.value!!, password = password.value!!)
                    emit(loginAuthResult)
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e.cause!!))
            }
        }
    }
}
