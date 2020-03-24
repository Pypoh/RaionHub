package com.example.raionhub.ui.main.scan.domain

import com.example.raionhub.repository.model.UserInRoom
import com.example.raionhub.utils.viewobject.Resource

interface IScan {

    suspend fun registerUserIntoRoom(nim: String): Resource<UserInRoom>
}