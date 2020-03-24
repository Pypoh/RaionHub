package com.example.raionhub.ui.main.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.raionhub.repository.model.UserInRoom
import com.example.raionhub.ui.main.scan.domain.IScan
import com.example.raionhub.utils.viewobject.Resource

class ScanViewModel(private val useCase: IScan) : ViewModel() {

    lateinit var result: LiveData<Resource<UserInRoom>>
    
}
