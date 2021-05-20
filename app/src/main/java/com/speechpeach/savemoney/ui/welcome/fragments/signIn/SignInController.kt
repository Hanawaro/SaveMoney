package com.speechpeach.savemoney.ui.welcome.fragments.signIn

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.speechpeach.savemoney.database.FirebaseModule
import com.speechpeach.savemoney.database.User

class SignInController(
    private val context: Context,
    private val activity: FragmentActivity,
    private val token: String
) {

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var activityForResult: ActivityResultLauncher<Intent>
    private lateinit var firebaseFirestore: FirebaseFirestore

    val firestore
        get() = firebaseFirestore

    var successCallback: ((forTheFirstTime: Boolean, referralCode: String, blocked: ArrayList<String>) -> Unit) = { _, _, _-> }
    var failedCallback: (() -> Unit) = {}

    fun init() {
        firebaseFirestore = FirebaseFirestore.getInstance()

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)

        activityForResult = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {  }
            }
        }
    }

    fun launch() {
        val signInIntent = googleSignInClient.signInIntent
        activityForResult.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        FirebaseModule.firebaseAuth.signInWithCredential(credential).addOnCompleteListener(activity) { task ->

            if (task.isSuccessful) {
                firebaseFirestore.collection(User.collection)
                    .whereEqualTo("email", FirebaseModule.firebaseAuth.currentUser!!.email)
                    .get().addOnSuccessListener {
                        if (!it.isEmpty) {
                            successCallback(it.isEmpty,
                                it.toObjects(User::class.java).first().referralCode,
                                it.toObjects(User::class.java).first().blocked
                            )
                        } else {
                            successCallback(it.isEmpty,
                                "",
                                ArrayList()
                            )
                        }
                    }
            } else {
                failedCallback()
            }
        }
    }


}