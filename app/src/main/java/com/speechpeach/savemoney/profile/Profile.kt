package com.speechpeach.savemoney.profile

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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

    fun setReferral(referral: String, successCallback: () -> Unit, failedCallback: () -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection(User.collection)
                .whereEqualTo("uid", referral)
                .get()
                .addOnSuccessListener { point ->
                    if (!point.isEmpty && referral != uid) {

                        firestore.collection(User.collection)
                                .whereEqualTo("uid", uid)
                                .get()
                                .addOnSuccessListener { current ->

                                    if (!current.isEmpty) {
                                        User.getAll(uid) {
                                            if (it.isEmpty) {
                                                user.referralCode = referral
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

                    } else {
                        failedCallback()
                    }
                }
    }

    fun setReferral(successCallback: () -> Unit, failedCallback: () -> Unit) {
        setReferral(referralCode, successCallback, failedCallback)
    }

    fun onChange(callback: (QuerySnapshot) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection(User.collection)
            .whereEqualTo("uid", uid)
            .addSnapshotListener { value, _ ->
                if (value != null)
                    callback(value)
            }
    }
}