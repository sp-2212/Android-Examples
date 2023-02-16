package com.techholding.android.examples

import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.core.Amplify
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.FirebaseApp

class Application : android.app.Application(){
    override fun onCreate() {
        super.onCreate()
        try {
            Amplify.configure(applicationContext)
            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }

        FirebaseApp.initializeApp(applicationContext)
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
}