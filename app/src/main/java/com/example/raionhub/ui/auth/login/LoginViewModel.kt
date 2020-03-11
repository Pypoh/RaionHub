package com.example.raionhub.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.raionhub.ui.auth.login.domain.ILogin
import com.example.raionhub.utils.viewobject.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val useCase: ILogin): ViewModel() {

    var errorPassword: MutableLiveData<String> = MutableLiveData()
    var errorEmail: MutableLiveData<String> = MutableLiveData()

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var busy: MutableLiveData<Int>? = null

    var result: MutableLiveData<Int> = MutableLiveData()

    fun loginWithEmailAndPassword(): LiveData<Resource<AuthResult?>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val eventList = useCase.loginWithEmailAndPassword(email = email.value!!, password = password.value!!)
                emit(eventList)
            }catch (e: Exception){
                emit(Resource.Failure(e.cause!!))
            }
        }
    }
}
