package com.example.raionhub.utils

class Constants {

    companion object {

        // Timeout / Delay
        const val AUTH_TIMEOUT: Long = 5000L
        const val SPLASH_SCREEN_DELAY: Long = 2000L

        // Intent Code
        const val INTENT_CAMERA_SCAN_REQUEST = 100

        // Permission Code
        const val CAMERA_PERMISSION_REQUEST = 101

        // Barcode Code
        const val IN_QR_CODE_TEXT = "6PiShkgFG0YEBCTTys22ZQ==" // Masuk, AES 256 bit Key: absensi_raion
        const val OUT_QR_CODE_TEXT = "ehIfH7WtNM21sA5ah+OSdQ==" // Keluar, AES 256 bit Key: absensi_raion

        // Firebase Collection
        const val ROOM_COLLECTION = "room"
        const val USERS_COLLECTION = "users"

        // Firestore User Document Column
        const val NIM = "nim"

        // Firestore Room Document Column
        const val TIME_ENTER = "time_enter"
        const val TIME_OUT = "time_out"
        const val TOTAL_TIME = "total_time"

    }
}
