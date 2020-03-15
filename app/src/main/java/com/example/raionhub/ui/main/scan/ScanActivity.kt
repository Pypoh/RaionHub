package com.example.raionhub.ui.main.scan

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraX
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.raionhub.R
import com.example.raionhub.utils.Constants
import com.example.raionhub.utils.toast
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.Executors

class ScanActivity : AppCompatActivity() {

    private lateinit var cameraPreview: PreviewView
    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var imagePreview: Preview
    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        cameraPreview = findViewById(R.id.scan_camera_view)

        if (isCameraPermissionGranted()) {
            cameraPreview.post {
                startCamera()
            }
        } else {
            requestCameraPermission()
        }

        // Init Camera
        initCamera()
    }

    private fun initCamera() {

        cameraProviderFuture = ProcessCameraProvider.getInstance(this)
    }

    @SuppressLint("RestrictedApi")
    private fun startCamera() {
        imagePreview = Preview.Builder().apply {
            setTargetAspectRatio(AspectRatio.RATIO_16_9)
            setTargetRotation(cameraPreview.display.rotation)
        }.build()
        imagePreview.setSurfaceProvider(cameraPreview.previewSurfaceProvider)

        val qrCodeAnalyzer = QrCodeAnalyzer { qrCodes ->
            Log.d("QrCodeAnalyzer", "working on activity")
            qrCodes.forEach {
                toast(it.rawValue.toString())
                Log.d("ScanActivity", "QR Code detected: ${it.rawValue}.")
            }
        }

        val imageAnalysis = ImageAnalysis.Builder().apply {
            setImageQueueDepth(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        }.build()

        imageAnalysis.setAnalyzer(executor, qrCodeAnalyzer)

        val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            cameraProvider.bindToLifecycle(this, cameraSelector, imagePreview, imageAnalysis)
        }, ContextCompat.getMainExecutor(this))
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            Constants.CAMERA_PERMISSION_REQUEST
        )
    }

    private fun isCameraPermissionGranted(): Boolean {
        val selfPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return selfPermission == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == Constants.CAMERA_PERMISSION_REQUEST) {
            if (isCameraPermissionGranted()) {
                cameraPreview.post {
                    startCamera()
                }
            } else {
                finish()
            }
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onDestroy() {
        super.onDestroy()
        CameraX.unbindAll()
    }
}
