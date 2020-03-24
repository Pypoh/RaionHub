package com.example.raionhub.ui.main.scan.domain

import com.example.raionhub.repository.datasource.remote.firestore.scan.IScanRepo

class ScanImpl(private val scanRepository: IScanRepo) : IScan {

    override suspend fun registerUserIntoRoom(nim: String) = scanRepository.registerUserIntoRoom(nim)

}