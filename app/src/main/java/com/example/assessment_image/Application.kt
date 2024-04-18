package com.example.assessment_image

import android.app.Application
import android.os.Build
import androidx.core.os.BuildCompat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        
    }
}