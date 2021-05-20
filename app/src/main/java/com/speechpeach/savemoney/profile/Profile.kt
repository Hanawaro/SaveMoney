package com.speechpeach.savemoney.profile

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.speechpeach.savemoney.database.FirebaseModule
import com.speechpeach.savemoney.database.User
import com.speechpeach.savemoney.repository.Repository

object Profile {

    private lateinit var profile: User

    fun init(user: User) { profile = user }

    val user
        get() = profile

    val uid
        get() = user.uid

    val email
        get() = user.email

    val name
        get() = user.name

    fun logout(context: Context, token: String) {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        FirebaseModule.firebaseAuth.signOut()
        googleSignInClient.signOut()
    }

}