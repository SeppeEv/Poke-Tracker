package com.example.application.ui

import android.app.Application
import com.example.application.data.AppContainer
import com.example.application.data.AppDataContainer

class MyApplication : Application() {
    val container: AppContainer by lazy {
        AppDataContainer(this)
    }
}