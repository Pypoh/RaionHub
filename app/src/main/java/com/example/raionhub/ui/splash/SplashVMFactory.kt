package com.example.raionhub.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.ui.auth.domain.IAuth

class SplashVMFactory(private val useCase: IAuth) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IAuth::class.java).newInstance(useCase)
    }
}