package com.example.anchor

import android.app.Application
import com.example.anchor.data.AppContainer
import com.example.anchor.data.DefaultAppContainer

class AnchorApplication : Application() {
    lateinit var container: AppContainer

     override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
