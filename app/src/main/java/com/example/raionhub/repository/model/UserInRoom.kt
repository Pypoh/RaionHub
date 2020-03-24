package com.example.raionhub.repository.model

import com.example.raionhub.utils.Constants
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserInRoom(
    @SerializedName(Constants.NIM)
    private var nim: String? = null,
    @SerializedName(Constants.TIME_ENTER)
    private var time_enter: Timestamp? = null
)