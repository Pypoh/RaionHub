package com.example.raionhub.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var errorPassword: MutableLiveData<String> = MutableLiveData()
    var errorEmail: MutableLiveData<String> = MutableLiveData()

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var busy: MutableLiveData<Int>? = null

    var result: MutableLiveData<Int> = MutableLiveData()
}
