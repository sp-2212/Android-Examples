package com.techholding.android.examples.utils

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object UserDetail {
    var currentUser = Firebase.auth.currentUser
    var authState =  MutableLiveData(checkUser())

    private fun checkUser(): Boolean
    {
        return currentUser != null
    }

    fun signOut()
    {
        Firebase.auth.signOut()
        authState.value = false
    }
}