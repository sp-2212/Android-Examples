package com.techholding.android.examples.ui.firebase.google

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.techholding.android.examples.utils.UserDetail

class GoogleViewModel : ViewModel() {

    val REQ_CODE: Int = 123
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var firebaseAuth: FirebaseAuth
    var authState =  MutableLiveData<Boolean>()

    init {
        authState.value = checkUser()
    }

    fun signOutGoogle()
    {
        Firebase.auth.signOut()
        authState.value = false
    }

    private fun checkUser(): Boolean
    {
        return UserDetail.currentUser != null
    }
}