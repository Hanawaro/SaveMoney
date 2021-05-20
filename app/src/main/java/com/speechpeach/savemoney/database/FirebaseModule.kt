package com.speechpeach.savemoney.database

import com.google.firebase.auth.FirebaseAuth

object FirebaseModule {

    private var singleFirebaseAuth: FirebaseAuth? = null
        get() {
            field = field ?: FirebaseAuth.getInstance()
            return field
        }
        private set

    val firebaseAuth: FirebaseAuth
        get() = singleFirebaseAuth!!

}