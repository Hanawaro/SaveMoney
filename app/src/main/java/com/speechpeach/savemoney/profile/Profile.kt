package com.speechpeach.savemoney.profile

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.speechpeach.savemoney.database.FirebaseModule
import com.speechpeach.savemoney.database.User

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

    val referralCode
        get() = user.referralCode

    fun logout(context: Context, token: String) {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        FirebaseModule.firebaseAuth.signOut()
        googleSignInClient.signOut()
    }

    fun setReferral(successCallback: () -> Unit, failedCallback: () -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection(User.collection)
            .whereEqualTo("uid", referralCode)
            .get()
            .addOnSuccessListener { point ->
                if (!point.isEmpty && referralCode != uid) {

                    firestore.collection(User.collection)
                        .whereEqualTo("uid", uid)
                        .get()
                        .addOnSuccessListener { current ->

                            if (!current.isEmpty) {
                                current.documents[0].reference.set(user)
                                successCallback()
                            } else {
                                failedCallback()
                            }
                        }

                } else {
                    failedCallback()
                }
            }
    }

}