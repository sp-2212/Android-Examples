package com.techholding.android.examples.utils

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object UserDetail {
    var currentUser = Firebase.auth.currentUser
}