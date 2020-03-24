package com.example.raionhub.ui.main.scan

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata

class QrCodeAnalyzer(
    private val onQrCodesDetected: (qrCodes: List<FirebaseVisionBarcode>) -> Unit
) : ImageAnalysis.Analyzer {

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(image: ImageProxy) {
        val rotationDegrees = image.imageInfo.rotationDegrees

        val option = FirebaseVisionBarcodeDetectorOptions.Builder()
                .setBarcodeFormats(FirebaseVisionBarcode.FORMAT_QR_CODE)
                .build()

        val detector = FirebaseVision.getInstance().getVisionBarcodeDetector(option)

        val rotation = rotationDegreesToFirebaseRotation(rotationDegrees)
        val visionImage = FirebaseVisionImage.fromMediaImage(image.image!!, rotation)

        detector.detectInImage(visionImage)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        // pass barcode data
                        onQrCodesDetected(barcodes)
                    }
                    image.close()
                }
                .addOnFailureListener {
                    image.close()
                }
    }

    private fun rotationDegreesToFirebaseRotation(rotationDegrees: Int): Int {
        return when (rotationDegrees) {
            0 -> FirebaseVisionImageMetadata.ROTATION_0
            90 -> FirebaseVisionImageMetadata.ROTATION_90
            180 -> FirebaseVisionImageMetadata.ROTATION_180
            270 -> FirebaseVisionImageMetadata.ROTATION_270
            else -> throw IllegalArgumentException("Not supported")
        }
    }
}
