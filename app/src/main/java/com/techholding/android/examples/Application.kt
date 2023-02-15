package com.techholding.android.examples

import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp

class Application : android.app.Application(){
    override fun onCreate() {
        super.onCreate()

        FacebookSdk.sdkInitialize(applicationContext)
        FirebaseApp.initializeApp(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}