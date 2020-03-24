package com.example.raionhub.ui.main.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.raionhub.ui.main.scan.domain.IScan

class ScanVMFactory(private val useCase: IScan) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IScan::class.java).newInstance(useCase)
    }
}